package ru.liveproduction.victoria.core.entity.localization.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;

import java.util.Optional;

@Repository
@Component
public interface IStoredLocaleRepository extends JpaRepository<StoredLocale, Integer> {

    Optional<IStoredLocale> getByJavaLanguageTagIgnoreCase(String javaLanguageTag);

}
