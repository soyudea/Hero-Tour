package co.com.udea.tourofhero.domain.service;

import co.com.udea.tourofhero.domain.errorhandler.BadResponseErrorHandler;
import co.com.udea.tourofhero.domain.model.Hero;
import co.com.udea.tourofhero.domain.model.enums.Responses;
import co.com.udea.tourofhero.domain.repository.HeroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {
    private final HeroRepository repository;

    @Autowired
    public HeroService(HeroRepository repository) {
        this.repository = repository;
    }

    public Hero getHeroByCodigo(Integer id) {
        return repository.getHeroById(id)
                .orElseThrow( () -> {
                    throw new BadResponseErrorHandler(Responses.NOT_FOUND_ENTITY.getMensaje(),
                            Responses.NOT_FOUND_ENTITY.getCodigo(),
                            Responses.NOT_FOUND_ENTITY.getHttpStatus());
                });
    }

    public List<Hero> getHeros() {
        return repository.getHeros()
                .orElseThrow( () -> {
                    throw new BadResponseErrorHandler(Responses.NOT_FOUND_ENTITIES.getMensaje(),
                            Responses.NOT_FOUND_ENTITIES.getCodigo(),
                            Responses.NOT_FOUND_ENTITIES.getHttpStatus());
                });
    }

    @Transactional
    public Hero saveHero(Hero hero) {
        return repository.save(hero)
                .orElseThrow( () -> {
                    throw new BadResponseErrorHandler(Responses.NOT_SAVE_ENTITY.getMensaje(),
                            Responses.NOT_SAVE_ENTITY.getCodigo(),
                            Responses.NOT_SAVE_ENTITY.getHttpStatus());
                });
    }

    @Transactional
    public boolean deleteHero(Integer id) {
        return repository.getHeroById(id).map(hero -> {
            repository.delete(id);
            return true;
        }).orElseThrow( () -> {
            throw new BadResponseErrorHandler(Responses.NOT_DELETE_ENTITY.getMensaje(),
                    Responses.NOT_DELETE_ENTITY.getCodigo(),
                    Responses.NOT_DELETE_ENTITY.getHttpStatus());
        });
    }

    @Transactional
    public Hero updateHero(Hero hero) {
        return repository.getHeroById(hero.getId()).map(data -> repository.update(hero).orElseThrow(() -> {
            throw new BadResponseErrorHandler(Responses.NOT_UPDATE_ENTITY.getMensaje(),
                    Responses.NOT_UPDATE_ENTITY.getCodigo(),
                    Responses.NOT_UPDATE_ENTITY.getHttpStatus());
        })).orElseThrow( () -> {
            throw new BadResponseErrorHandler(Responses.NOT_UPDATE_ENTITY.getMensaje(),
                    Responses.NOT_UPDATE_ENTITY.getCodigo(),
                    Responses.NOT_UPDATE_ENTITY.getHttpStatus());
        });
    }

    public List<Hero> searchHeroesContainsTerm(String term) {
        return repository.getHeroesContainsTerm(term)
                .orElseThrow( () -> {
                    throw new BadResponseErrorHandler(Responses.NOT_FOUND_ENTITIES.getMensaje(),
                            Responses.NOT_FOUND_ENTITIES.getCodigo(),
                            Responses.NOT_FOUND_ENTITIES.getHttpStatus());
                });
    }
}
