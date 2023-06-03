package goodReadClone.WebProject.service;

import goodReadClone.WebProject.entity.Shelf;
import goodReadClone.WebProject.entity.ShelfInstance;
import goodReadClone.WebProject.repository.ShelfInstanceRepository;
import org.springframework.stereotype.Service;

@Service
public class ShelfInstanceService {
    private ShelfInstanceRepository shelfInstanceRepository;

    public void save(ShelfInstance s) {
        shelfInstanceRepository.save(s);
    }

    public void delete(ShelfInstance si) {
        shelfInstanceRepository.delete(si);
    }
}
