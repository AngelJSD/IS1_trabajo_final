package repository;

import java.util.Collection;

import domain.Account;

public interface AccountRepository extends BaseRepository<Account, Long> {
	Account findByNumber(String number);

	Collection<Account> findByPersonId(Long personId);
}
