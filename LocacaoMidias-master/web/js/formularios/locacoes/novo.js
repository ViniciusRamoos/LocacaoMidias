
$( () => {
    
    let itensLocacao = [];
    
    // formatadores
    let fmtMoeda = new Intl.NumberFormat( 
        "pt-BR", {
            style: "currency",
            currency: "BRL"
        }
    );
    
    let fmtNumero = new Intl.NumberFormat( 
        "pt-BR", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }
    );
    
    $( "#btnInserir" ).on( "click", event => {
        
        let $selectExemplar = $( "#selectExemplar" );
        
        let codigoInterno = $selectExemplar.val();
        let valorVenda = $selectExemplar.find( ":selected" ).data( "valor" ).toString();
        let titulo = $selectExemplar.find( ":selected" ).data("titulo");
        let descricao = $selectExemplar.find( ":selected" ).data("descricao");

        if ( $selectExemplar.find( ":selected" ).is(':disabled') ) {
            alert('Este exemplar já foi adicionado');
        } else {

            if ( valorVenda.includes( "," ) ) {
                valorVenda = valorVenda.replace( ",", "." );
            }
            
            itensLocacao.push({
                        codigoInterno: codigoInterno,
                        valorVenda: valorVenda,
                        descricao: descricao,
                        titulo: titulo
                    });
                    
            atualizarGUI();
    
            $selectExemplar.find( ":selected" ).prop('disabled', true);

        }
        
        

    });
    
    $( "#btnRemover" ).on( "click", event => {
        
        let selecao = $( "#selectItensLocacao" ).val();
        
        // se não selecionou nenhum
        if ( selecao.length === 0 ) {
            alert( "Selecione um item da locação para remover!" );
            
            //se há seleção
        } else if ( confirm( "Deseja remover o(s) item(ns) da locação selecionado(s)?" ) ) {
            
            // itera pela seleção
            for ( let i = 0; i < selecao.length; i++ ) {
                
                // busca sequencial nos itens de venda
                for ( let j = 0; j < itensLocacao.length; j++ ) {
                    
                    let item = itensLocacao[j];
                    
                    // encontrou?
                    if ( selecao[i] === item.idProduto ) {
                        
                        // remove da posição j
                        itensLocacao.splice( j, 1 );
                        break;
                        
                    }
                    
                }
                
            }
            
            // remonta a lista
            atualizarGUI();
            
        }
        
    });
    
    // ao clicar no botão limpar
    $( "#btnLimpar" ).on( "click", event => {

        if ( confirm( "Deseja remover todos os itens da locação?" ) ) {
            
            for( let i=0; i<itensLocacao.length; i++ ) {

                idHabilitar = itensLocacao[i].codigoInterno;

                $( "#selectExemplar" ).find( `option[value="${idHabilitar}"]` ).prop('disabled', false);

            }

            itensLocacao = [];
            atualizarGUI();

        }

    });
    
    // submissão da venda
    $( "#formNovaLocacao" ).on( "submit", event => {
        
        if ( $( "#selectItensLocacao > option" ).length === 0 ) {
            alert( "Uma locação precisa conter pelo menos um exemplar!" );
            return false;
        }
        
        return true;
        
    });
    
    
    // constrói as opções do <select> (lista) de itens de venda;
    // atualiza o valor total da venda;
    // e prepara os dados para envio
    let atualizarGUI = () => {
        
        let $select = $( "#selectItensLocacao" );
        let total = new Decimal( 0 );
        
        $select.html( "" );
        
        itensLocacao.forEach( item => {
            
            let valorItem = new Decimal( item.valorVenda );
            
            $opt = $( "<option></option>" ).
                    html( `${item.titulo} - ` + 
                    `${item.descricao} - ` + 
                    `${fmtMoeda.format( valorItem )}` ).
                    val( `${item.codigoInterno}` );
            
            $select.append( $opt );
            total = total.plus( valorItem );
            
        });
        
        $( "#divTotal" ).html( "Total: " + fmtMoeda.format( total ) );
        $( "#hiddenItensLocacao" ).val( JSON.stringify( itensLocacao ) );
        
    };
    
});
