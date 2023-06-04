package goodReadClone.WebProject.service;

import goodReadClone.WebProject.entity.ShelfInstance;
import goodReadClone.WebProject.repository.ShelfInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
