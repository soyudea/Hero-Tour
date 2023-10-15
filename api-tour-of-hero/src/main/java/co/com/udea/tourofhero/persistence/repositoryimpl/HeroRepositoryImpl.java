package co.com.udea.tourofhero.persistence.repositoryimpl;

import co.com.udea.tourofhero.domain.model.Hero;
import co.com.udea.tourofhero.domain.repository.HeroRepository;
import co.com.udea.tourofhero.persistence.crud.HeroCrud;
import co.com.udea.tourofhero.persistence.entity.HeroEntity;
import co.com.udea.tourofhero.persistence.mapper.HeroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HeroRepositoryImpl implements HeroRepository {

    private final HeroMapper mapper;

    private final HeroCrud persistence;

    @Autowired
    public HeroRepositoryImpl(HeroMapper mapper, HeroCrud persistence) {
        this.mapper = mapper;
        this.persistence = persistence;
    }

    @Override
    public Optional<Hero> getHeroById(Integer id) {
        return persistence.findHeroEntitiesById(id)
                .map(mapper::toHero);
    }

    @Override
    public Optional<List<Hero>> getHeros() {
        List<HeroEntity> list = persistence.findAll();
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toHeroes(list));
    }

    @Override
    public Optional<Hero> save(Hero hero) {
        HeroEntity entity = mapper.toHeroEntity(hero);
        return Optional.of(mapper.toHero(persistence.save(entity)));
    }

    @Override
    public void delete(Integer id) {
        persistence.deleteHeroEntitiesById(id);
    }

    @Override
    public Optional<Hero> update(Hero hero) {
        HeroEntity entity = mapper.toHeroEntity(hero);
        HeroEntity origin = persistence.findHeroEntitiesById(hero.getId()).orElse(new HeroEntity());
        origin.setId(entity.getId());
        origin.setName(entity.getName());
        return Optional.of(mapper.toHero(persistence.save(origin)));
    }

    @Override
    public Optional<List<Hero>> getHeroesContainsTerm(String term) {
        List<HeroEntity> list = persistence.findAllByNameIsContainingIgnoreCase(term);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toHeroes(list));
    }
}
