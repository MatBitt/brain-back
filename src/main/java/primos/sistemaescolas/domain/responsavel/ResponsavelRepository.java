package primos.sistemaescolas.domain.responsavel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository extends JpaRepository<Responsavel, String> {

    public Responsavel findByCpf(String cpf);
}
