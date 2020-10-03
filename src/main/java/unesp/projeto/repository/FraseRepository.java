package unesp.projeto.repository;
import org.springframework.stereotype.Repository;
import unesp.projeto.entity.Frase;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FraseRepository extends JpaRepository<Frase, Long> {
}
