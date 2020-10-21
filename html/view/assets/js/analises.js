window.onload = getHist()

const histSelection = document.getElementById('table-hist').getElementsByTagName('tbody')[0];

function getHist()
{
    const query = `
    query getAnalises($id_usuario: ID!){
        getAnalises(id_usuario: $id_usuario)
        {
            id
            frase
            {
                texto
            }
            sentimento
            {
                nome
            }
            dataAnalise
        }
    }
    `
    const variaveis = {id_usuario: localStorage.id}

    queryFetch(query, variaveis).then(data =>{
            data.data.getAnalises.forEach(analise => {
                histSelection.insertRow().innerHTML = 
                "<td>" + analise.frase.texto + "</td>"+
                "<td>" + analise.sentimento.nome + "</td>"+
                "<td>" + analise.dataAnalise + "</td>"
            // console.log(analise.dataAnalise) 
            })
        })
}

function queryFetch(query_entry, variables_entry)
{
    return fetch('http://localhost:8088/graphql',
    {
        method: 'POST',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            query: query_entry,
            variables: variables_entry
        })
    }).then(res => res.json())
}