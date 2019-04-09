package br.com.reciclagem.web.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.reciclagem.web.entidade.MaterialEntidade;
import br.com.reciclagem.web.repository.MaterialRepository;
import br.com.reciclagem.web.vo.MaterialVO;

public class MaterialService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private MaterialRepository materialRepository;
	
	public void gravar(MaterialVO materialVO){
		
		MaterialEntidade materialEntidade = parseMaterialVoParaEntidade(materialVO);
		materialRepository.gravar(materialEntidade);
		
	}
	
	public List<MaterialVO> consultarTodos() throws Exception{
		
		List<MaterialVO> listaMaterialVO = new ArrayList<MaterialVO>();
		List<MaterialEntidade> listaEntidade = materialRepository.getFindAll();
		
		for(MaterialEntidade entidade : listaEntidade){
			listaMaterialVO.add(parseMaterialEntidadeParaVo(entidade));
		}
		
		return listaMaterialVO;
		
	}
	
	

	private MaterialEntidade parseMaterialVoParaEntidade(MaterialVO materialVO) {
		MaterialEntidade materialEntidade = new MaterialEntidade();
		materialEntidade.setCodigo(materialVO.getCodigo());
		materialEntidade.setDescricao(materialVO.getDescricao());
		materialEntidade.setNome(materialVO.getNome());
		materialEntidade.setQuantidadeEmEstoque(materialVO.getQuantidadeEmEstoque());
		materialEntidade.setQuantidadeMinimaParaVenda(materialVO.getQuantidadeMinimaParaVenda());
		materialEntidade.setUnidadeMedida(materialVO.getUnidadeMedida());
		materialEntidade.setValorCompra(materialVO.getValorCompra());
		materialEntidade.setValorVenda(materialVO.getValorVenda());
		
		return materialEntidade;
	}
	
	private MaterialVO parseMaterialEntidadeParaVo(MaterialEntidade materialEntidade) {
		
		MaterialVO materialVO = new MaterialVO();
		materialVO.setCodigo(materialEntidade.getCodigo());
		materialVO.setDescricao(materialEntidade.getDescricao());
		materialVO.setNome(materialEntidade.getNome());
		materialVO.setQuantidadeEmEstoque(materialEntidade.getQuantidadeEmEstoque());
		materialVO.setQuantidadeMinimaParaVenda(materialEntidade.getQuantidadeMinimaParaVenda());
		materialVO.setUnidadeMedida(materialEntidade.getUnidadeMedida());
		materialVO.setValorCompra(materialEntidade.getValorCompra());
		materialVO.setValorVenda(materialEntidade.getValorVenda());
		
		return materialVO;
	}

}
