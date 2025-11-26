package dk.ek.bcrafteksamensprojekt.service;

import dk.ek.bcrafteksamensprojekt.exceptions.NotFoundException;
import dk.ek.bcrafteksamensprojekt.model.Material;
import dk.ek.bcrafteksamensprojekt.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;

    public Material saveMaterial(Material material){
        return materialRepository.save(material);
    }

    public Material updateMaterial(Long id, Material updated){
        Material existing = materialRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Materiale ikke fundet med id "+ id));

        existing.setName(updated.getName());
        existing.setUnit(updated.getUnit());
        existing.setPricePerUnit(updated.getPricePerUnit());

        return materialRepository.save(existing);
    }

    public Material findMaterialById(Long id){
        return materialRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Materiale ikke fundet med id "+ id));
    }
    //Changed method to include "whole-word" matches i.e. Plywood won't be found under wood and "skruetrækker" won't be found under "skrue"
    public List<Material> findMaterialsByName(String name) {
        String search = name.toLowerCase();

        return materialRepository.findAll().stream()
                .filter(m -> {
                    if (m.getName() == null) return false;
                    //this "//s+" regex split words from 1 word "oak wood" in to "oak" and "wood"
                    String[] words = m.getName().toLowerCase().split("\\s+");
                    for (String w : words) {
                        if (w.equals(search)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }


    public void deleteMaterialById(Long id){
        Material material = materialRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Materiale ikke fundet med id "+id));
        materialRepository.delete(material);
    }
}
