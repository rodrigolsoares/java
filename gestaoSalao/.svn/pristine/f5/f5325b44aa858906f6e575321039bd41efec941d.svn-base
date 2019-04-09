package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.ProdutoDao;
import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.vo.ProdutoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Repository
public class ProdutoDaoJdbc extends SpringDao implements ProdutoDao {
	
	DataUtil manipula = new DataUtil();
	
	public void inseriProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();

		sb.append(" insert into produto ( pk_produto, nm_produto, marca, descricao, qtde_estoque, valor_pago, ");
		sb.append(" qtde_minima, valor_total, fk_status, fk_responsavel_salao, fk_salao, fk_usuario) values ");
		sb.append("(?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ? )" );
			
			
		Object[] param = new Object[]{
			Constantes.ZERO,	
			produto.getNomeProduto(),
			produto.getMarca(),
			produto.getDescricao(),
			produto.getQteEstoque(),
			produto.getValorPago(),
			produto.getQteMinimaEstoque(),
			produto.getValorTotal(),
			produto.getStatus(),
			usuario.getResponsavel().getCodigo(),
			usuario.getSalao().getCodigo(),
			usuario.getCodigo()
			
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
			
		
	}

	public void atualizaProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();

		sb.append(" update produto set ");
		sb.append(" nm_produto = ? ,");
		sb.append(" marca = ? ,");
		sb.append(" descricao = ? ,");
		sb.append(" qtde_estoque = ? ,");
		sb.append(" valor_pago = ? ,");
		sb.append(" qtde_minima = ? ,");
		sb.append(" valor_total = ? ,");
		sb.append(" fk_status = ? ,");
		sb.append(" fk_usuario = ? ");
		sb.append("where pk_produto = ? ");
		
		Object[] param = new Object[]{
			
			produto.getNomeProduto(),
			produto.getMarca(),
			produto.getDescricao(),
			produto.getQteEstoque(),
			produto.getValorPago(),
			produto.getQteMinimaEstoque(),
			produto.getValorTotal(),
			produto.getStatus(),
			usuario.getCodigo(),
			produto.getCodigo()
		
		};
			
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
			
		
	}

	public boolean verificaProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append("select pk_produto from produto where nm_produto = ? ");
		sb.append(" and fk_salao = ? and fk_responsavel_salao = ? ");
		
		try{
			
			if (produto.getFlagOperacao() == RegraNegocioConstantes.PESQUISAR_REGISTRO_INCLUINDO_A_CHAVE){
				sb.append(" and pk_produto <> " + produto.getCodigo());
			}
			
			Object[] param = new Object[]{
					
				produto.getNomeProduto(),
				usuario.getSalao().getCodigo(),
				usuario.getResponsavel().getCodigo()
			};
			
			getJdbcTemplate().queryForInt(sb.toString().toLowerCase(), param);
			return true;
			
		} catch (EmptyResultDataAccessException e){
			return false;
		}

	}
	
	public List<ProdutoVO> pesquisaProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
		
		sb.append(" select pk_produto, nm_produto, marca, descricao, qtde_estoque, valor_pago, qtde_minima, valor_total, fk_status ");
		sb.append(" from produto");
		sb.append(" where fk_salao = ? and fk_responsavel_salao = ?  ");
		
		if(!"".equals(produto.getNomeProduto()) && produto.getNomeProduto() != null)
			sb.append(" and nm_produto like '%" + produto.getNomeProduto() + "%' ");
		
		if(!"".equals(produto.getMarca()) && produto.getMarca() != null)
			sb.append(" and marca like '%" + produto.getMarca() + "%' ");
		
		if(produto.getQteEstoque() > 0)
			sb.append(" and qtde_estoque like '%" + produto.getQteEstoque() + "%' ");
		
		if (produto.getStatus() != 0)
			sb.append(" and fk_status = '" + produto.getStatus() + "' ");
		
		if (produto.getVerifica() == RegraNegocioConstantes.HABILITA_PESQUISA_PRODUTO_FALTANTE)
			sb.append(" and qtde_estoque < qtde_minima ");
		
		if (produto.getVerifica() == RegraNegocioConstantes.HABILITA_PESQUISA_PRODUTO_NAO_FALTANTE)
			sb.append(" and qtde_estoque > qtde_minima ");
		
		Object[] param = new Object[]{
			usuario.getSalao().getCodigo(),
			usuario.getResponsavel().getCodigo()
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapper());
		
	}

	public ProdutoVO pesquisaProdutoPorCodigo(String codigo, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_produto, nm_produto, marca, descricao, qtde_estoque, valor_total, valor_pago, qtde_minima, fk_status ");
		sb.append(" from produto");
		sb.append(" Where pk_produto = ? ");
		
		try{
			
			return (ProdutoVO)getJdbcTemplate().query(sb.toString().toLowerCase(), new Object[]{codigo},new rowMapper());
		
		} catch (EmptyResultDataAccessException e){
			return new ProdutoVO();
		}
	}
	
	private static final class rowMapper implements RowMapper<ProdutoVO> {
		public ProdutoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			ProdutoVO po = new ProdutoVO();
			po.setCodigo(rs.getString("pk_produto"));
			po.setNomeProduto(rs.getString("nm_produto"));
			po.setMarca(rs.getString("marca"));
			po.setDescricao(rs.getString("descricao"));
			po.setQteEstoque(rs.getInt("qtde_estoque"));
			po.setQteMinimaEstoque(rs.getInt("qtde_minima"));
			po.setValorPago(rs.getDouble("valor_pago"));
			po.setValorTotal(rs.getDouble("valor_total"));
			po.setStatus(rs.getInt("fk_status"));
			
			return po;
		}
	}


}
