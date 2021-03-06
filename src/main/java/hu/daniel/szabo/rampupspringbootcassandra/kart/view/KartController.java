package hu.daniel.szabo.rampupspringbootcassandra.kart.view;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import hu.daniel.szabo.rampupspringbootcassandra.kart.domain.Kart;
import hu.daniel.szabo.rampupspringbootcassandra.kart.domain.KartRepository;

@RestController
@Transactional
public class KartController {

    private KartRepository kartRepository;

    public KartController(KartRepository kartRepository) {
        this.kartRepository = kartRepository;
    }

    @GetMapping("/kart")
    public Collection<KartView> getAll() {
        return StreamSupport.stream(kartRepository.findAll().spliterator(), false).map(KartView::new).collect(Collectors.toList());
    }

    @GetMapping("/kart/{id}")
    public ResponseEntity<KartView> get(@PathVariable UUID id) {
        Kart kart = kartRepository.findOne(id);
        if (kart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new KartView(kart), HttpStatus.OK);
    }

    @PutMapping("/kart")
    public ResponseEntity<?> put(@RequestBody @Valid KartView kartView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Kart kart = new Kart();
        kart.setNumber(kartView.getNumber());
        kart.setEngineSize(kartView.getEngineSize());
        kartRepository.save(kart);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/kart/{id}")
    public ResponseEntity<KartView> post(@PathVariable UUID id, @RequestBody @Valid KartView kartView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Kart kart = kartRepository.findOne(id);
        if (kart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        kart.setNumber(kartView.getNumber());
        kart.setEngineSize(kartView.getEngineSize());
        Kart updatedKart = kartRepository.save(kart);
        return new ResponseEntity<>(new KartView(updatedKart), HttpStatus.OK);
    }

    @DeleteMapping("/kart/{id}")
    public void delete(@PathVariable UUID id) {
        kartRepository.delete(id);
    }
}
