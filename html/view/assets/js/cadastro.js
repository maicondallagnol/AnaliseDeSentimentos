function addContato(email_entry, celular_entry, id_usuario_entry)
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
    const variaveis = {
        email: email_entry, 
        celular: password_entry, 
        id_usuario: id_usuario_entry}

    return queryFetch(mutation, variaveis)
}

function startCadastro()
{
    nome_entry = document.getElementById('nome').value
    sobrenome_entry = document.getElementById('sobrenome').value
    email_entry = document.getElementById('email').value
    celular_entry = document.getElementById('celular').value
    username_entry = document.getElementById('username').value
    data = {'nome': nome_entry, 'sobrenome': sobrenome_entry, 'email': email_entry,
            'celular':celular_entry, 'username':usuario_entry, }
    checkLogin(data)

}

function checkLogin(data)
{
    query = `
    query existsUsername($username: String!)
    {
        existsUsername(username: $username)
    }
    `
    variaveis = {username: data['username']}

    queryFetch(query, variaveis).then(result =>{
        if(data.data.existsLogin)
        {
            setUsuarioExistente()
        }
        else
        {
            addUsuario(data['nome'],data['senha'],data['email']).then(result =>{
                addLogin(data['username'] )
            })
            
        }
    });
}

function setUsuarioExistente()
{
    // const email = document.getElementById('email')
    const usuario = document.getElementById('usuario')

    // email.value = ""
    usuario.value = ""


    usuario.placeholder = "Username já existente!"
    // email.placeholder = "Email/Usuário já existente!"
}

function addUsuario(userName_entry, password_entry, email_entry)
{
    const mutation = `
    mutation addUsuario($nomeUsuario: String!, $senha: String!, $email: String!){
        addLogin(nomeUsuario: $nomeUsuario, senha: $senha, email: $email)
        {
            id
        }
    }
    `
    const variaveis = {nomeUsuario: userName_entry, senha: password_entry, email: email_entry}

    return queryFetch(mutation, variaveis)
}

function addLogin(userName_entry, password_entry, id_usuario_entry)
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