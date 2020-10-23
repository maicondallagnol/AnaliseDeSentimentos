function autenticarLogin()
{
    userName_entry = document.getElementById("user").value
    password_entry = document.getElementById("pass").value

    const query = `
    query autenticarLogin($username: String!, $senha: String!){
        autenticarLogin(username: $username, senha: $senha)
        {
           id
        }
    }
    `
    const variaveis = {username: userName_entry, senha: password_entry}

    queryFetch(query, variaveis).then(data => {
        if(!data.data)
        {
            const user = document.getElementById("user")
            const pass = document.getElementById("pass")

            user.value = ""
            user.placeholder = "Usuário/Senha Incorretos"
            pass.value = ""
            pass.placeholder = "Usuário/Senha Incorretos"

        }
        else
        {
            localStorage.clear()
            localStorage.id = data.data.autenticarLogin.id
            document.location.href = "index.html"
        }

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