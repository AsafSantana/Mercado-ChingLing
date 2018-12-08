package br.com.ufms.compras;

import br.com.ufms.compras.entity.Categoria;
import br.com.ufms.compras.entity.Cidade;
import br.com.ufms.compras.entity.Cliente;
import br.com.ufms.compras.entity.Endereco;
import br.com.ufms.compras.entity.Estado;
import br.com.ufms.compras.entity.ItemPedido;
import br.com.ufms.compras.entity.Pagamento;
import br.com.ufms.compras.entity.PagamentoComBoleto;
import br.com.ufms.compras.entity.PagamentoComCartao;
import br.com.ufms.compras.entity.Pedido;
import br.com.ufms.compras.entity.Produto;
import br.com.ufms.compras.entity.enumeration.EstadoPagamentoEnum;
import br.com.ufms.compras.entity.enumeration.TipoClienteEnum;
import br.com.ufms.compras.repository.CategoriaRepository;
import br.com.ufms.compras.repository.CidadeRepository;
import br.com.ufms.compras.repository.ClienteRepository;
import br.com.ufms.compras.repository.EnderecoRepository;
import br.com.ufms.compras.repository.EstadoRepository;
import br.com.ufms.compras.repository.ItemPedidoRepository;
import br.com.ufms.compras.repository.PagamentoRepository;
import br.com.ufms.compras.repository.PedidoRepository;
import br.com.ufms.compras.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class ComprasApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ComprasApplication.class, args);
    }

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    public void run(String... strings) throws Exception {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "escritório");
        Categoria cat3 = new Categoria(null, "Cozinha");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        categoriaRepository.save(Arrays.asList(cat1, cat2, cat3));


        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        produtoRepository.save(Arrays.asList(p1, p2, p3));

    }
}
