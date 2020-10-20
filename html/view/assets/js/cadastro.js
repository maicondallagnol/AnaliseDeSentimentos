function addContato(email_entry, celular_entry, id_usuario_entry )
{
    const mutation = `
    mutation addContato($email: String!, $celular: String!, $id_usuario: ID!){
        addContato(email: $email, celular: $celular, id_usuario: $id_usuario)
        {
            id
            email
            celular
            usuario
            {
                id
                nome
                sobrenome
            }
        }
    }
    `
    const variaveis = {email: userName_entry, celular: password_entry, id_usuario: id_usuario_entry}

    return queryFetch(mutation, variaveis)
}

function addLogin(userName_entry, password_entry, id_usuario_entry )
{
    const mutation = `
    mutation addLogin($nomeUsuario: String!, $senha: String!, $id_usuario: ID!){
        addLogin(nomeUsuario: $nomeUsuario, senha: $senha, id_usuario: $id_usuario)
        {
            id
            nomeUsuario
            senha
            usuario
            {
                id
                nome
                sobrenome
            }
        }
    }
    `
    const variaveis = {nomeUsuario: userName_entry, senha: password_entry, id_usuario: id_usuario_entry}

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