window.onload = getImages()


function getImages()
{
    query = `
        query getImages
        {
            getImages
            {
                id
                nome
                caminho
            }
        }
    `
    const variaveis = {}

    queryFetch(query, variaveis).then(data =>{ 
            data.data.getImages.forEach(imagem => {
                var div = document.createElement("div")
                div.className = "col-lg-2"

                var div2 = document.createElement("div")
                div2.className = "card mb-3"

                var div3 = document.createElement("div")
                div3.className="btn-group" 
                div3.toggleAttribute = "buttons"

                const selectImg = document.getElementById("selectImg")
                const label = document.createElement("label")
                const input = document.createElement("input")

                label.className = "btn btn-primary"
                label.name = imagem.nome
                label.id = "dog" + imagem.id
                input.type="radio"
                input.name="selectDog"
                input.value = imagem.id
                input.style.display = "none"

                input.onclick = function (){
                    var imgNotSelect = document.querySelectorAll('input[type=radio]')
                    imgNotSelect.forEach(img2 => {
                        const img = document.getElementById("dog"+img2.value)
                        img.className = "btn btn-primary"
                    });

                    var imgSelect = document.querySelector('input[name="selectDog"]:checked').value
                    const img = document.getElementById("dog"+imgSelect)
                    img.className = "btn btn-danger"
                }

                var img = document.createElement("img")
                img.src = imagem.caminho;
                img.className = "rounded-circle"
                img.width = "100"
                img.height = "100"
                
                label.appendChild(img)
                label.appendChild(input)
                div3.appendChild(label)
                div2.appendChild(div3)
                div.appendChild(div2)
                

                selectImg.appendChild(div)
                
            })
        })
    
}


function saveSentimento()
{
    const id_imagem_entry = document.querySelector('input[name="selectDog"]:checked').value
    const nome_entry = document.getElementById("nome").value
    const descricao_entry = document.getElementById("descricao").value

    mutation=`
    mutation addSentimento($nome: String!, $descricao: String!, $id_imagem: ID!)
    {
        addSentimento(nome: $nome, descricao: $descricao, id_imagem: $id_imagem)
    }
    `

    variaveis = {nome: nome_entry, descricao: descricao_entry, id_imagem: id_imagem_entry}

    queryFetch(mutation, variaveis).then( data => {
        if(data.data.addSentimento)
        {
            const obrigado = document.getElementById("obrigado")
            obrigado.innerHTML = ''
            obrigado.style.visibility = "visible"
            const obrigado_msg = document.createElement("h3")
            let node = document.createTextNode("Sentimento Cadastrado!")
            obrigado_msg.appendChild(node)

            obrigado.appendChild(obrigado_msg)
        }
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