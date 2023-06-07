package goodReadClone.WebProject.service;

import goodReadClone.WebProject.DTO.RequestDTO;
import goodReadClone.WebProject.entity.Author;
import goodReadClone.WebProject.entity.Request;
import goodReadClone.WebProject.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    public void addRequest(RequestDTO requestDTO, Author author) {
        Request request = new Request(requestDTO.getEmail(), requestDTO.getPhone(), requestDTO.getMessage(), requestDTO.getDate(), requestDTO.getStatus(),author);
        requestRepository.save(request);
    }

    public List<Request> getRequests() {
        return requestRepository.findAll();
    }

    public void updateRequest(RequestDTO requestDTO, Author author) {
        Request request = requestRepository.findByEmail(requestDTO.getEmail());
        request.setStatusString("CONFIRMED");
        requestRepository.save(request);
    }

    public void updateRequestDeny(RequestDTO requestDTO, Author author) {
        Request request = requestRepository.findByEmail(requestDTO.getEmail());
        request.setStatusString("DENIED");
        requestRepository.save(request);
    }
}
