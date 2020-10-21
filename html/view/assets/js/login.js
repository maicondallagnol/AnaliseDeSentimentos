function autenticarLogin()
{
    userName_entry = document.getElementById("user").value
    password_entry = document.getElementById("pass").value

    console.log(userName_entry)
    console.log(password_entry)

    const query = `
    query autenticarLogin($nomeUsuario: String!, $senha: String!){
        autenticarLogin(nomeUsuario: $nomeUsuario, senha: $senha)
        {
           id
        }
    }
    `
    const variaveis = {nomeUsuario: userName_entry, senha: password_entry}

    queryFetch(query, variaveis).then(data => {
        localStorage.clear()
        localStorage.id = data.data.autenticarLogin.id
        document.location.href = "index.html"
    })
}

function getUsuarioLogin(id_login_entry)
{

    const query = `
    query getUsuarioLogin($id_login: ID!){
        getUsuarioLogin(id_login: $id_login)
        {
           id
        }
    }
    `
    const variaveis = {$id_login: id_login_entry}

    queryFetch(query, variaveis).then(data => {
        localStorage.clear()
        localStorage.id = data.data.autenticarLogin.usuario.id
        document.location.href = "login.html"
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