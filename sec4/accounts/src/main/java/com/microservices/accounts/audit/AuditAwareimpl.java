package com.microservices.accounts.audit;





import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
@Component("auditAwareimpl")
public class AuditAwareimpl implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		
		  return Optional.of("ACCOUNTS_MS");
	}

	
}
