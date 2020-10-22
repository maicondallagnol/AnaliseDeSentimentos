window.onload = getBarItens()


function getBarItens()
{
    query = `
    query getUsuario($id_usuario: ID!)
    {
        getUsuario(id_usuario: $id_usuario)
        {
            nome
        }
    }
    `

    variaveis = {id_usuario: localStorage.id}

    queryFetch(query, variaveis).then(data => 
        {
            if(!data.data.getUsuario){
                document.location.href = "login.html"
            }
            const nome_head = document.getElementById("nome_head")
            nome_head.innerHTML = data.data.getUsuario.nome
        })
}


function exit() {localStorage.id = 0}


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