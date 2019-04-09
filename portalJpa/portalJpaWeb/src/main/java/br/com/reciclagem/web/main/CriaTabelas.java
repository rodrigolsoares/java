package br.com.reciclagem.web.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.reciclagem.web.entidade.MenuEntidade;

public class CriaTabelas {
	
	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("ReciclagemDB");

	    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReciclagemDB");
	    EntityManager manager = factory.createEntityManager();
	    
	    manager.getTransaction().begin(); 
	    
	    MenuEntidade menuClientePai = new MenuEntidade();
	    menuClientePai.setCodigo(1);
	    menuClientePai.setNome("Cliente");
	    menuClientePai.setUrl("#");
	    menuClientePai.setIcone("#");
	    menuClientePai.setIdObjeto("idCli");
	    menuClientePai.setNivel(0);
	    manager.persist(menuClientePai);
	    
	    MenuEntidade menuMaterialPai = new MenuEntidade();
	    menuMaterialPai.setCodigo(2);
	    menuMaterialPai.setNome("Material");
	    menuMaterialPai.setUrl("#");
	    menuMaterialPai.setIcone("#");
	    menuMaterialPai.setIdObjeto("idPro");
	    menuMaterialPai.setNivel(0);
	    manager.persist(menuMaterialPai);
	    
	    MenuEntidade menuFornecedorPai = new MenuEntidade();
	    menuFornecedorPai.setCodigo(3);
	    menuFornecedorPai.setNome("Fornecedor");
	    menuFornecedorPai.setUrl("#");
	    menuFornecedorPai.setIcone("#");
	    menuFornecedorPai.setIdObjeto("idFor");
	    menuFornecedorPai.setNivel(0);
	    manager.persist(menuFornecedorPai);
	    
	    MenuEntidade menuCadastroMaterialFilho = new MenuEntidade();
	    menuCadastroMaterialFilho.setCodigo(4);
	    menuCadastroMaterialFilho.setNome("Cadastro");
	    menuCadastroMaterialFilho.setUrl("cadastroMaterial.jsf");
	    menuCadastroMaterialFilho.setIcone("ui-icon-disk");
	    menuCadastroMaterialFilho.setIdObjeto("idCadMat");
	    menuCadastroMaterialFilho.setNivel(2);
	    manager.persist(menuCadastroMaterialFilho);
	    
	    MenuEntidade menuCompraMaterialFilho = new MenuEntidade();
	    menuCompraMaterialFilho.setCodigo(5);
	    menuCompraMaterialFilho.setNome("Compra");
	    menuCompraMaterialFilho.setUrl("#");
	    menuCompraMaterialFilho.setIcone("#");
	    menuCompraMaterialFilho.setIdObjeto("idComMat");
	    menuCompraMaterialFilho.setNivel(2);
	    manager.persist(menuCompraMaterialFilho);
	    
	    MenuEntidade menuConsultaMaterialFilho = new MenuEntidade();
	    menuConsultaMaterialFilho.setCodigo(6);
	    menuConsultaMaterialFilho.setNome("Consulta");
	    menuConsultaMaterialFilho.setUrl("consultaMaterial.jsf");
	    menuConsultaMaterialFilho.setIcone("ui-icon-folder-open");
	    menuConsultaMaterialFilho.setIdObjeto("idConsulMat");
	    menuConsultaMaterialFilho.setNivel(2);
	    manager.persist(menuConsultaMaterialFilho);
	    
	    MenuEntidade menuVendaMaterialFilho = new MenuEntidade();
	    menuVendaMaterialFilho.setCodigo(7);
	    menuVendaMaterialFilho.setNome("Venda");
	    menuVendaMaterialFilho.setUrl("#");
	    menuVendaMaterialFilho.setIcone("#");
	    menuVendaMaterialFilho.setIdObjeto("idVenMat");
	    menuVendaMaterialFilho.setNivel(2);
	    manager.persist(menuVendaMaterialFilho);
	      
	    
	    manager.getTransaction().commit();  


	    manager.close();
		
		
		System.exit(0);
	}

}
