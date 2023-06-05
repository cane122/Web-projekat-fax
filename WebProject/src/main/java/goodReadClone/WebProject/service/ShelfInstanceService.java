package goodReadClone.WebProject.service;

import goodReadClone.WebProject.entity.ShelfInstance;
import goodReadClone.WebProject.repository.ShelfInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShelfInstanceService {
    @Autowired
    private ShelfInstanceRepository shelfInstanceRepository;

    public ShelfInstance save(ShelfInstance s) {
        return shelfInstanceRepository.saveAndFlush(s);
    }

    public void delete(Long id) {
        shelfInstanceRepository.deleteById(id);
    }

    public Optional<ShelfInstance> findById(Long idShelfInstance) {
        return shelfInstanceRepository.findById(idShelfInstance);
    }
}
