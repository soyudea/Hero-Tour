package co.com.udea.tourofhero.persistence.mapper;

import co.com.udea.tourofhero.domain.model.Hero;
import co.com.udea.tourofhero.persistence.entity.HeroEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HeroMapper {
    Hero toHero(HeroEntity heroEntity);

    @InheritInverseConfiguration
    @Mapping(target = "code", ignore = true)
    HeroEntity toHeroEntity(Hero hero);

    List<Hero> toHeroes (List<HeroEntity> heroEntities);
}
