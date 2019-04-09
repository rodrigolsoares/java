package br.com.reciclagem.web.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.reciclagem.web.service.MaterialService;
import br.com.reciclagem.web.util.MensagensUtil;
import br.com.reciclagem.web.vo.MaterialVO;


@Named
@SessionScoped
public class MaterialMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private MaterialVO materialVO;
	private List<MaterialVO> listaMaterial;
	
	@Inject
	private MaterialService materialService;
	
	@PostConstruct
	public void init() throws Exception {
		this.materialVO = new MaterialVO();
		this.listaMaterial = materialService.consultarTodos();
	}
	 
	public void gravar() throws Exception{
		this.materialService.gravar(materialVO);
		MensagensUtil.info("Produto Cadastrado com sucesso");
		this.init();
	}

	public MaterialVO getMaterialVO() {
		return materialVO;
	}

	public void setMaterialVO(MaterialVO materialVO) {
		this.materialVO = materialVO;
	}

	public List<MaterialVO> getListaMaterial() {
		return listaMaterial;
	}

	public void setListaMaterial(List<MaterialVO> listaMaterial) {
		this.listaMaterial = listaMaterial;
	}
	
	
	
	
	/*@PreDestroy
	public void destroy(){
		System.out.println("Teste destroy");
	}*/
	
}
