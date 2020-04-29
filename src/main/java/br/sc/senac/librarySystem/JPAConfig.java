package br.sc.senac.librarySystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

public class JPAConfig {

	@Configuration
	@EnableJpaAuditing(auditorAwareRef = "auditorAware")
	public class JpaConfig {
		@Bean
		public AuditorAware<String> auditorAware() {
			return new AuditorAwareImpl();
		}
	}
}
