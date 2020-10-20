function fazAnalise()
{
    const text = document.getElementById("texto").value
    saveFrase(text).then(data => {
        makeAnalise(data.data.addFrase.id, localStorage.id).then(data =>
            {
                // console.log(data.data.makeAnalise.id)
                document.location.href = "correto-incorreto.html?idanalise=" + encodeURIComponent(data.data.makeAnalise.id);
            })
    })
}

function makeAnalise(id_frase_entry, id_usuario_entry)
{
    const mutation = `
    mutation makeAnalise($id_frase: ID!, $id_usuario: ID!){
        makeAnalise(id_frase: $id_frase, id_usuario: $id_usuario)
        {
            id
            sentimento
            {
                emoticon
            }
        }
    }
    `
    const variaveis = {id_frase: id_frase_entry, id_usuario: id_usuario_entry}

    return queryFetch(mutation, variaveis)
}

function saveFrase(texto_entry)
{

    const mutation = `
    mutation addFrase($texto: String!){
        addFrase(frase: $texto){
            id
            texto
        }
      }
    `

    const variaveis = {texto: texto_entry}

    return queryFetch(mutation, variaveis)
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