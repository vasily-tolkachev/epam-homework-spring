package lab7.dao.jpa;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import lombok.Cleanup;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@FunctionalInterface
public interface JpaDao {

    EntityManagerFactory getEmf();

    @SneakyThrows
    default <T> T mapEntityManager(CheckedFunction1<EntityManager, T> entityManagerMapper) {
        @Cleanup EntityManager entityManager = getEmf().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            T t = entityManagerMapper.apply(entityManager);
            transaction.commit();
            return t;
        } catch (Throwable throwable) {
            transaction.rollback();
            throw throwable;
        }
    }

    default void withEntityManager(CheckedConsumer<EntityManager> entityManagerConsumer) {
        mapEntityManager(entityManager -> {
            entityManagerConsumer.accept(entityManager);
            return null;
        });
    }
}
