package repository.jpa;

import java.util.Collection;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import repository.AccountRepository;
import domain.Account;

@Repository
public class JpaAccountRepository extends JpaBaseRepository<Account, Long> implements
		AccountRepository {

	@Override
	public Account findByNumber(String number) {
		//SELECT a.id, a.number, a.date FROM tbl_account a WHERE a.number = :number
		String jpaQuery = "SELECT a FROM Account a WHERE a.number = :number";
		TypedQuery<Account> query = entityManager.createQuery(jpaQuery, Account.class);
		query.setParameter("number", number);
		return getFirstResult(query);
	}

	@Override
	public Collection<Account> findByPersonId(Long personId) {
		String jpaQuery = "SELECT a FROM Account a JOIN a.owners p WHERE p.id = :personId";
		TypedQuery<Account> query = entityManager.createQuery(jpaQuery, Account.class);
		query.setParameter("personId", personId);
		return query.getResultList();
	}
}
