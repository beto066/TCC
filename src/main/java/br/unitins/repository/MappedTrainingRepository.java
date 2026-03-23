package br.unitins.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.model.MappedTrainingResult;
import br.unitins.model.enums.TrainingResult;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MappedTrainingRepository implements PanacheRepository<MappedTrainingResult>  {
    public Map<String, Double> findStatistics(Long authorId) throws Exception {

        String jpql = """
            SELECT m.result, COUNT(m)
            FROM MappedTrainingResult m
            WHERE m.training.author.id = :authorId
            GROUP BY m.result
        """;

        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("authorId", authorId);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        Map<String, Double> statisticsMap = new HashMap<>();

        for (TrainingResult result : TrainingResult.values()) {
            statisticsMap.put(result.getLabel(), 0.0);
        }

        Double sum = 0.0;

        for (Object[] result : results) {
            TrainingResult trainingResult;
            Double count;

            if (result[0] instanceof TrainingResult) {
                trainingResult = (TrainingResult) result[0];
            } else {
                throw new Exception("Invalid query type");
            }

            if (result[1] instanceof Long) {
                count = ((Long) result[1]).doubleValue();
                sum += count;
            } else {
                throw new Exception("Invalid query type");
            }

            statisticsMap.put(trainingResult.getLabel(), count);
        }

        if (results.isEmpty()) {
            statisticsMap.put("average", 0.0);
        } else {
            statisticsMap.put("average", sum / results.size());
        }

        return statisticsMap;
    }

    public boolean isPresent(Integer position, Integer noteId) {
        return find(
            "position = ?1 AND training.id = ?2",
            position,
            noteId
        ).singleResultOptional().isPresent();
    }

    public MappedTrainingResult findByPosition(Integer position, Long noteId) {
        return find(
            "position = ?1 AND training.id = ?2",
            position,
            noteId
        ).firstResult();
    }

    public MappedTrainingResult persistOrUpdate(MappedTrainingResult mapped) {
        if (mapped == null || mapped.id == null) {
            throw new IllegalArgumentException("Entidade ou chave composta não pode ser null");
        }

        if (isPresent(mapped.position, mapped.result.getId())) {
            return getEntityManager().merge(mapped);
        } else {
            persist(mapped);
            return mapped;
        }
    }
}
