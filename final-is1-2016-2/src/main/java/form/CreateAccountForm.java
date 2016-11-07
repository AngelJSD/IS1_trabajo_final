package form;

import java.util.Collection;

import domain.Account;

public class CreateAccountForm {
	private Collection<Long> ownerIds;

	private Account account;

	public Collection<Long> getOwnerIds() {
		return ownerIds;
	}

	public void setOwnerIds(Collection<Long> ownerIds) {
		this.ownerIds = ownerIds;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
