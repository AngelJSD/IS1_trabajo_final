package service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.AccountRepository;
import domain.Account;
import domain.Person;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	

	@Transactional
	public void save(Account account) {
		accountRepository.persist(account);
	}

	/*@Transactional
	public void createAccount(Collection<Long> ownerIds, Account account) {
		if (!ownerIds.isEmpty()) {
			Collection<Person> owners = personRepository.findByIds(ownerIds);
			account.setOwners(owners);
			accountRepository.persist(account);
		}
	}*/

	public Collection<Account> getAccountsByPersonId(Long personId) {
		return accountRepository.findByPersonId(personId);
	}

	/*public Collection<Account> getAccountsByPersonId2(Long personId) {
		Person person = personRepository.find(personId);
		if (person != null) {
			return person.getAccounts();
		}
		return Collections.emptyList();
	}*/

	public Collection<Account> getAccounts() {
		return accountRepository.findAll();
	}
}
