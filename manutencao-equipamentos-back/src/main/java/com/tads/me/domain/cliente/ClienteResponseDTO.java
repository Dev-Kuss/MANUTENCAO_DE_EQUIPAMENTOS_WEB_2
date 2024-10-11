package com.tads.me.domain.cliente;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        Integer cep,
        String logradouro,
        String complemento,
        String unidade,
        String bairro,
        String localidade,
        String uf,
        String estado,
        String regiao,
        String ibge,
        String gia,
        String ddd,
        String siafi
) {
    public ClienteResponseDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getCep(),
                cliente.getLogradouro(),
                cliente.getComplemento(),
                cliente.getUnidade(),
                cliente.getBairro(),
                cliente.getLocalidade(),
                cliente.getUf(),
                cliente.getEstado(),
                cliente.getRegiao(),
                cliente.getIbge(),
                cliente.getGia(),
                cliente.getDdd(),
                cliente.getSiafi()
        );
    }
}
