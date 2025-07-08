package com.example.Biblioteca.service;

public class SoPraAna {

    /*
    @Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    private LivroDTO toDTO(Livro livro) {
        LivroDTO dto = new LivroDTO();
        dto.setId(livro.getId());;
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setAno(livro.getAno());
        return dto;
    }

    private Livro toEntity(LivroDTO dto) {
        Livro livro = new Livro();
        livro.setId(dto.getId());
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setAno(dto.getAno());
        return livro;
    }

    public List<LivroDTO> listar() {
        return livroRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    public Optional<LivroDTO> buscar(Long id) { return livroRepository.findById(id).map(this::toDTO); }

    public LivroDTO salvar(LivroDTO dto) {
        Livro livro = toEntity(dto);
        return toDTO(livroRepository.save(livro));
    }

    public LivroDTO atualizar(Long id, LivroDTO dto) {
        dto.setId(id);
        Livro livro = toEntity(dto);
        return toDTO(livroRepository.save(livro));
    }

    public void deletar(Long id) {livroRepository.deleteById(id); }
}

     */
}
