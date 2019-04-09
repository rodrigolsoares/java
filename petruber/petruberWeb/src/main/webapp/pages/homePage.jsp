<%@ include file="/resources/template/importTags.jsp"%>

<tiles:insertDefinition name="template">
	
	<tiles:putAttribute name="body">
		
		<div class="page-header">
	      <h1>Criar Sistema</h1>
	    </div>
	    
	    <div class="input-group input-group-sm">
	      <span class="input-group-addon" id="sizing-addon3">Caminho Arquivo</span>
	      <input type="text" class="form-control" placeholder="Digite o caminho do arquivo" aria-describedby="sizing-addon3">
	    </div>
	    
	    <br />
	    
	    <button type="button" class="btn btn-default btn-lg">
	      <span class="glyphicon glyphicon-star" aria-hidden="true"></span> Criar Sistema
	    </button>
	    
	</tiles:putAttribute>

</tiles:insertDefinition>

