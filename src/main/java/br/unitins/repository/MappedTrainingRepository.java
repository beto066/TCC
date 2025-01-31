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
    public Map<String, Double> findStatistics() throws Exception {
        String jpql = "SELECT m.id.result, COUNT(m) as countResults FROM MappedTrainingResult m GROUP BY m.id.result";
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
}
