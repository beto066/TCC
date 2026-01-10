package br.unitins.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.model.MappedTrainingKey;
import br.unitins.model.MappedTrainingResult;
import br.unitins.model.enums.TrainingResult;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MappedTrainingRepository implements PanacheRepository<MappedTrainingResult>  {
    public Map<String, Double> findStatistics() throws Exception {
        String jpql = "SELECT m.result, COUNT(m) as countResults FROM MappedTrainingResult m GROUP BY m.result";
        Query query = getEntityManager().createQuery(jpql);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        Map<String, Double> statisticsMap = new HashMap<>();

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
                count = 1.0 * (Long) result[1];
                sum += count;
            } else {
                throw new Exception("Invalid query type");
            }

            statisticsMap.put(trainingResult.getLabel(), count);
        }

        statisticsMap.put("average", sum/results.size());

        return statisticsMap;
    }

    public boolean isPresent(MappedTrainingKey serializable) {
        return find(
            "id.trainingId = ?1 AND id.position = ?2",
            serializable.trainingId,
            serializable.position
        ).singleResultOptional().isPresent();
    }

    public MappedTrainingResult find(MappedTrainingKey id) {
        return find(
            "id.trainingId = ?1 AND id.position = ?2",
            id.trainingId,
            id.position
        ).singleResult();
    }

    public MappedTrainingResult persistOrUpdate(MappedTrainingResult mapped) {
        if (mapped == null || mapped.id == null) {
            throw new IllegalArgumentException("Entidade ou chave composta não pode ser null");
        }

        if (isPresent(mapped.id)) {
            return getEntityManager().merge(mapped);
        } else {
            persist(mapped);
            return mapped;
        }
    }
}
