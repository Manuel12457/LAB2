package com.example.lab2.Controllers;

import com.example.lab2.Entity.Marcas;
import com.example.lab2.repository.MarcasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    MarcasRepository marcasRepository;

    @GetMapping("")
    public String listarMarcas(Model model) {
        List<Marcas> listaMarcas = marcasRepository.findAll();
        model.addAttribute("listaMarcas", listaMarcas);
        return "marcas/listarMarcas";
    }

    @GetMapping("/nuevo")
    public String newform() {
        return "marcas/newForm";
    }

    @PostMapping("/save")
    public String saveShip(Marcas marcas, RedirectAttributes a, Model model, @RequestParam("s") Integer s) {

        if (marcas.getId() == 0) { //Hay que crear
            a.addFlashAttribute("msg", "0");
        } else { //Hay que actualizar
            a.addFlashAttribute("msg", "1");
        }
        marcasRepository.save(marcas);
        return "redirect:/marcas";
    }

    @GetMapping("/editar")
    public String editarForm(@RequestParam("id") Integer id, Model model, RedirectAttributes a) {
        Optional<Marcas> optionalMarcas = marcasRepository.findById(id);
        if (optionalMarcas.isPresent()) {
            Marcas marcas = optionalMarcas.get();
            model.addAttribute("marcas", marcas);
            return "marcas/editForm";
        } else {
            a.addFlashAttribute("msg", -1); //Ocurrio un error al momento de querer editar
            return "redirect:/marcas";
        }
    }

    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") Integer id, RedirectAttributes a) {
        Optional<Marcas> optionalMarcas = marcasRepository.findById(id);
        if (optionalMarcas.isPresent()) {
            a.addFlashAttribute("msg", "2"); //Marca borrada exitosamente
            marcasRepository.deleteById(id);
        } else {
            a.addFlashAttribute("msg", "-2"); //Ocurrio un error al momento de querer borrar
        }
        return "redirect:/marcas";
    }

}