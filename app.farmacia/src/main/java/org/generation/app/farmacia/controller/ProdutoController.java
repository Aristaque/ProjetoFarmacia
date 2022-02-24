package org.generation.app.farmacia.controller;

import org.generation.app.farmacia.model.Produto;
import org.generation.app.farmacia.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;
    @GetMapping
    public ResponseEntity<List<Produto>> findAllProduto(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findByIdProduto (@PathVariable Long id){
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> findByNomeProduto(@PathVariable String nome){
        return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Produto> post (@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
    }
    @PutMapping
    public ResponseEntity<Produto> put (@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }


}
