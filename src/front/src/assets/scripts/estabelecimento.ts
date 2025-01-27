import { jwtDecode } from "jwt-decode";
import Cookies from "js-cookie";
import bcrypt from "bcryptjs";
import Chart from 'chart.js/auto';

const token = Cookies.get("token");
const emailParam = document.getElementById("email-param").textContent;
const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get("establishmentId");
const apiUrl = import.meta.env.PUBLIC_API_URL;

if (token) {
  interface JwtPayload {
    type: number;
    sub: string;
    iat: number;
    exp: number;
    id: number;
  }

  const decodedToken: JwtPayload = jwtDecode(token);
  const type = decodedToken.type;
  const email = decodedToken.sub;

  let pwd = "";
  let banner = "";
  let profile = "";
  let data_criacao = "";
  let isUserAcc = true;

  fetch(`${apiUrl}/estabelecimento/${emailParam}`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => response.json())
    .then((data) => {
      pwd = data.senhaEstabelecimento;
      data_criacao = data.dataCriacaoEstabelecimento;

      document.getElementById("profile-name").textContent =
        data.nomeEstabelecimento;
      document.getElementById("profile-description").textContent =
        data.descricaoEstabelecimento;
      (document.getElementById("profile-img") as HTMLImageElement).alt =
        `Foto de ${data.nomeEstabelecimento}`;

        console.log(data);
        

      document.getElementById("profile-score").innerHTML = `<p class="font-semibold text-yellow-400">★<span class="text-gray-900 ml-1">${data.notaEstabelecimento}</span></p>`;

      // Mostrar informações do Estabelecimento
      document.getElementById("business-info").classList.remove("hidden");

      document.getElementById("business-images").classList.remove("hidden");

      // Remove o último ponto e vírgula e divide a string por ";"
      const enderecoArray = data.localidadeEstabelecimento.split(";");

      // Desestrutura os valores
      const [regiao, bairro, rua, numero, complemento] = enderecoArray;

      // Formata o endereço de forma legível
      const enderecoFormatado = `${rua}, ${numero}${complemento && complemento.trim() !== "" ? ", " + complemento : ""} - ${bairro}, Região ${regiao}`;
      document.getElementById("location").textContent = enderecoFormatado;
      document.getElementById("tel").textContent =
        data.telefoneEstabelecimento;
      document.getElementById("email").textContent =
        data.emailEstabelecimento;
      document.getElementById("category").textContent =
        data.categoriaEstabelecimento;

      // Exibir a tela de loading no início
      const loadingScreen = document.getElementById("loading-screen");

      // Função para ocultar a tela de loading
      const hideLoadingScreen = () => {
        if (loadingScreen) {
          loadingScreen.style.display = "none";
        }
      };

      Promise.all([
        // Verifica se o perfil é do usuário logado
        verify_useracc(data),
        
        // Carregar dados do formulario de edição
        load_formdata(data),

        // Carregar avaliaçoes
        load_reviews(),

        // Carregar reservas
        load_reservas(),

        // Carregar as imagens
        load_images(data),

        // Carregar mesas
        load_tables(data),

        // Carrega o carrossel de imagens
        load_carousel(),

        // Carrega as tags
        load_tags(),

        // Carregar indicadores
        load_indices()
      ])
        .then(() => {
          hideLoadingScreen();
        })
        .catch((error) => {
          console.error("Erro ao carregar recursos:", error);
          hideLoadingScreen();
        });

    })
    .catch((error) => console.error("Erro:", error));

  const verify_useracc = (data) => {
    if (email != data.emailEstabelecimento) {
      document.getElementById("tag-form").classList.add("hidden");
      document.getElementById("image-form").classList.add("hidden");
      document.getElementById("add-tag-action").classList.add("hidden");
      document.getElementById("profile-actions").classList.add("hidden");
      document.getElementById("add-image-action")?.classList.add("hidden");
      document.getElementById("delete-modal-image")?.classList.add("hidden");
      document.getElementById("reservas-radio").classList.add("hidden");
      const reservasRadio = document.getElementById("reservas-radio");
      if (reservasRadio) {
        reservasRadio.style.display = "none";
        const reservasLabel = document.querySelector(`label[for="reservas-radio"]`) as HTMLElement;
        if (reservasLabel) {
          reservasLabel.style.display = "none";
        }
      }
      const indicesRadio = document.getElementById("indices-radio");
      if (indicesRadio) {
        indicesRadio.style.display = "none";
        const indicesLabel = document.querySelector(`label[for="indices-radio"]`) as HTMLElement;
        if (indicesLabel) {
          indicesLabel.style.display = "none";
        }
      }
      if (decodedToken.type != 1) {
        document.getElementById("review-action")?.classList.remove("hidden");
      }
      document.getElementById("table-action")?.classList.remove("hidden");
      isUserAcc = false;
    }
  };

  const load_tags = async () => {
    try {
      const response = await fetch(
        `${apiUrl}/estabelecimento/${id}/tags`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (!response.ok) {
        throw new Error("Erro ao carregar tags");
      }

      const data = await response.json();
      const tagsContainer = document.getElementById("business-tags");
      document.getElementById("tag-form").classList.add("hidden");
      tagsContainer.innerHTML = ""; // Limpa qualquer conteúdo anterior

      // Itera pelas tags e adiciona ao container
      data.forEach((tag) => {
        const tagItem = document.createElement("li");
        tagItem.className = `tag-item flex items-center border border-gray-300 rounded-full px-3 py-1 m-1 hover:bg-orange-600 hover:text-white transition-colors`;

        const tagName = document.createElement("span");
        tagName.textContent = tag.nomeTag;
        tagName.className = "mr-2";
        tagItem.appendChild(tagName);

        if (isUserAcc) {
          const deleteIcon = document.createElement("i");
          deleteIcon.className = `delete-icon cursor-pointer text-white group-hover:inline`;
          deleteIcon.innerText = "×";

          deleteIcon.addEventListener("click", () => delete_tag(tag.nomeTag));
          tagItem.appendChild(deleteIcon);
        }

        tagsContainer.appendChild(tagItem);
      });
    } catch (error) {
      console.error("Erro:", error);
    }
  };

  // Função para converter blobImagem em base64
  const convertToBase64 = (imageBlob) => {
    return `data:image/png;base64,${imageBlob}`;
  };

  const load_carousel = async () => {
    try {
      const response = await fetch(
        `${apiUrl}/estabelecimento/${id}/imagens`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (!response.ok) {
        throw new Error("Erro ao carregar o carrossel");
      }

      const data = await response.json();

      // Obtém o contêiner onde as imagens serão carregadas
      document.getElementById("image-form").classList.add("hidden");
      const imageRow = document.getElementById("image-row");
      imageRow.innerHTML = ""; // Limpa o conteúdo anterior

      // Adiciona cada imagem retornada ao contêiner
      data.forEach((item) => {
        const imgElement = document.createElement("img");
        imgElement.src = convertToBase64(item.blobImagem);
        imgElement.id = "carrossel-img-" + item.idImagem;
        imgElement.classList.add(
          "h-40",
          "mx-2",
          "object-cover",
          "rounded",
          "cursor-pointer"
        );

        // Adiciona um evento de clique para abrir o modal
        imgElement.addEventListener("click", () => {
          const imageModal = document.getElementById(
            "modal-image"
          ) as HTMLImageElement;
          imageModal.src = imgElement.src; // Define a imagem no modal
          imageModal.alt = item.idImagem;
          toggleModalImage();
        });

        imageRow.appendChild(imgElement);
      });

      // Exibe a seção de imagens
      document.getElementById("business-images").classList.remove("hidden");
    } catch (error) {
      console.error("Erro:", error);
    }
  };

  let isModalImageOpen = false;

  const toggleModalImage = () => {
    isModalImageOpen = !isModalImageOpen;
    const modal = document.getElementById("image-modal");
    modal.classList.toggle("hidden", !isModalImageOpen);
  };

  document
    .getElementById("close-modal")
    .addEventListener("click", toggleModalImage);

  // Adicione o evento para excluir a imagem ao clicar no botão
  document
    .getElementById("delete-modal-image")
    .addEventListener("click", () => {
      const imageModal = document.getElementById(
        "modal-image"
      ) as HTMLImageElement;

      delete_image(imageModal.alt); // Chama a função de deletar com o ID da imagem atual
      toggleModalImage(); // Fecha o modal após a exclusão
    });

  const imagensEmProcessoDeletar = new Set();

  const delete_image = async (imageId) => {
    // Verifica se a imagem já está sendo deletada para evitar duplicação
    if (imagensEmProcessoDeletar.has(imageId)) {
      console.warn("A imagem já está em processo de exclusão:", imageId);
      return;
    }

    // Marca a imagem como em processo de exclusão
    imagensEmProcessoDeletar.add(imageId);

    try {
      // Requisição de exclusão
      const response = await fetch(
        `${apiUrl}/estabelecimento/${id}/imagens/${imageId}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (!response.ok) {
        throw new Error("Erro ao deletar a imagem");
      } else {
        // Recarrega o carrossel se a exclusão for bem-sucedida
        load_carousel();
      }
    } catch (error) {
      console.error("Erro:", error.message);
    } finally {
      // Remove a imagem do set, permitindo futuras exclusões
      imagensEmProcessoDeletar.delete(imageId);
    }
  };

  const load_images = (data) => {
    // Exibir imagens
    banner = data.imagemBanner.blobImagem;
    profile = data.imagemPerfil.blobImagem;

    const bannerImg = convertToBase64(banner);
    const perfilImg = convertToBase64(profile);

    (document.getElementById("banner-img") as HTMLImageElement).src =
      bannerImg;
    (document.getElementById("profile-img") as HTMLImageElement).src =
      perfilImg;
  };

  const load_tables = (data) => {
    const container = document.getElementById("tables-container");
  
    if (!container) return;
  
    // Limpa o container antes de adicionar os novos cards
    container.innerHTML = "";

    if (data.mesasEstabelecimento.length === 0) {
      container.innerHTML = "<p class='text-gray-600 text-center w-full'>Nenhuma mesa encontrada.</p>";
      return;
    }
  
    // Itera sobre as mesas e cria cards para cada uma
    data.mesasEstabelecimento.forEach((table) => {
      // Cria o card da mesa
      const card = document.createElement("div");
      card.classList.add(
        "table-card",
        "w-48",            // Largura fixa de 12rem (48)
        "min-h-60",        // Altura ajustada para acomodar os botões
        "bg-gray-100",     // Cor de fundo clara
        "border",
        "border-gray-300",
        "rounded-lg",      // Bordas arredondadas
        "flex",
        "flex-col",
        "items-center",
        "justify-between", // Justify-between para distribuir melhor os elementos
        "p-4",             // Padding interno
        "shadow-lg",       // Sombra
        "transition-transform",
        "transform",
        "hover:scale-105"  // Efeito de zoom no hover
      );
  
      // Título do card
      const title = document.createElement("h3");
      title.textContent = `${table.nomeMesa}`;
      title.classList.add("text-lg", "font-semibold", "mb-2", "text-gray-700");
      card.appendChild(title);
  
      // Descrição da mesa
      const description = document.createElement("p");
      description.textContent = `${table.descricaoMesa}`;
      description.classList.add(
        "w-full",
        "text-sm",
        "text-gray-600",
        "text-center",
        "mb-2",
        "break-words",  // Força quebra de palavras longas
        "hyphens-auto"  // Opcional: adiciona hífens automáticos
      );
      card.appendChild(description);
  
      // Capacidade da mesa
      const capacity = document.createElement("p");
      capacity.innerHTML = `<span class="font-bold">Capacidade:</span> ${table.capacidadeMesa}`;
      capacity.classList.add("text-sm", "text-gray-600", "text-center");
      card.appendChild(capacity);
  
      // Se o usuário tiver permissão, adiciona os botões de editar e deletar 
      if (isUserAcc) {
        const buttonContainer = document.createElement("div");
        buttonContainer.classList.add("flex", "gap-2", "mt-4");
  
        // Botão de Editar
        const editButton = document.createElement("button");
        editButton.textContent = "Editar";
        editButton.classList.add(
          "bg-orange-600",
          "text-white",
          "px-3",
          "py-1",
          "rounded",
          "hover:bg-orange-700",
          "transition-colors"
        );
        editButton.onclick = () => openTableModal(table.idMesa, table.nomeMesa, table.descricaoMesa, table.capacidadeMesa, true);
        buttonContainer.appendChild(editButton);
  
        // Botão de Deletar
        const deleteButton = document.createElement("button");
        deleteButton.textContent = "Deletar";
        deleteButton.classList.add(
          "bg-gray-600",
          "text-white",
          "px-3",
          "py-1",
          "rounded",
          "hover:bg-gray-700",
          "transition-colors"
        );
        deleteButton.onclick = () => openDeleteConfirmationModal(table.idMesa, table.nomeMesa);
        buttonContainer.appendChild(deleteButton);
  
        card.appendChild(buttonContainer);
      } else if (type != 1) {
        // Se o usuário não tiver permissão, adiciona um botão de reservar
        const reserveButton = document.createElement("button");
        reserveButton.textContent = "Reservar";
        reserveButton.classList.add(
          "bg-orange-600",
          "text-white",
          "px-3",
          "py-1",
          "rounded",
          "hover:bg-orange-700",
          "transition-colors",
          "mt-4"
        );
        
        reserveButton.onclick = () => openReservaModal(table.idMesa, table.nomeMesa);
        card.appendChild(reserveButton);
      }
  
      // Adiciona o card ao container
      container.appendChild(card);
    });
  };    

  const load_indices = async () => {
    try {
      const response = await fetch(`${apiUrl}/indices/${id}`, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const indices = await response.json();

      // Criar gráfico de Distribuição de Notas (Gráfico com fundo laranja)
      const distribuicaoNotas = indices['Distribuição de Notas'];

      // Configuração do gráfico de pizza (Distribuição de Notas)
      const canvasDistribuicaoNotas = document.getElementById('distribuicao-notas-chart') as HTMLCanvasElement;
      canvasDistribuicaoNotas.width = 50;
      canvasDistribuicaoNotas.height = 50;
      const ctxDistribuicaoNotas = canvasDistribuicaoNotas.getContext('2d');
      new Chart(ctxDistribuicaoNotas, {
        type: 'pie',
        data: {
          labels: ['1-2', '3-4', '5'],
          datasets: [{
            data: [
              distribuicaoNotas ? distribuicaoNotas['1-2'] || 0 : 0,
              distribuicaoNotas ? distribuicaoNotas['3-4'] || 0 : 0,
              distribuicaoNotas ? distribuicaoNotas['5'] || 0 : 0,
            ],
            backgroundColor: ['#EA580C', '#FB923C', '#F97316'],
          }],
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: 'top',
            },
            tooltip: {
              callbacks: {
                label: (context) => {
                  const value = context.raw;
                  const total = context.chart.data.datasets[0].data.reduce((acc: number, curr: number) => acc + curr, 0);
                  const percentage = ((Number(value) / Number(total)) * 100).toFixed(1);
                  return `${value} (${percentage}%)`;
                }
              }
            }
          }
        }
      });

      // Criar gráfico de Fluxo de Reservas por Horário (Gráfico com fundo laranja)
      const fluxoReservas = indices['Fluxo de reservas por horário'];
      const canvasFluxoHorarios = document.getElementById('fluxo-horario-chart') as HTMLCanvasElement;
      canvasFluxoHorarios.width = 150;
      canvasFluxoHorarios.height = 100;
      const ctxFluxoHorarios = canvasFluxoHorarios.getContext('2d');
      new Chart(ctxFluxoHorarios, {
        type: 'line',
        data: {
          labels: fluxoReservas ? Object.keys(fluxoReservas).map((hour) => `${hour}h`) : [],
          datasets: [{
            label: 'Fluxo de Reservas por Hora',
            data: fluxoReservas ? Object.values(fluxoReservas) : [0],
            borderColor: '#EA580C',
            backgroundColor: 'rgba(234, 88, 12, 0.2)',
            fill: true,
            tension: 0.4,
          }],
        },
        options: {
          responsive: true,
          scales: {
            x: {
              title: {
                display: true,
                text: 'Hora do Dia'
              }
            },
            y: {
              title: {
                display: true,
                text: 'Reservas'
              }
            }
          }
        }
      });

      // Criar os 3 boxes com os valores desejados
      const indicadoresGerais = document.getElementById('indicadores-gerais');
      indicadoresGerais.innerHTML = ""; // Limpa o conteúdo antes de adicionar os novos boxes

      // Box para Avaliações mensais
      const boxAvaliacoes = document.createElement('div');
      boxAvaliacoes.classList.add('bg-orange-600', 'text-white', 'p-4', 'rounded-lg', 'mb-4');
      boxAvaliacoes.innerHTML = `
      <h5 class="text-lg font-bold">Avaliações mensais</h5>
      <p class="text-2xl">${indices['Avaliações por mês']?.[new Date().getMonth() +1] ?? 0}</p>
    `;

      // Box para Clientes por mesa
      const boxClientesMesa = document.createElement('div');
      boxClientesMesa.classList.add('bg-orange-600', 'text-white', 'p-4', 'rounded-lg', 'mb-4');
      boxClientesMesa.innerHTML = `
      <h5 class="text-lg font-bold">Média Clientes por mesa</h5>
      <p class="text-2xl">${indices['Capacidade média por mesa'] ?? 0}</p>
    `;

      // Box para Reservas totais
      const boxReservas = document.createElement('div');
      boxReservas.classList.add('bg-orange-600', 'text-white', 'p-4', 'rounded-lg', 'mb-4');
      boxReservas.innerHTML = `
      <h5 class="text-lg font-bold">Reservas totais</h5>
      <p class="text-2xl">${indices['Quantidade de reservas'] ?? 0}</p>
    `;

      // Adicionar os boxes ao container
      indicadoresGerais.appendChild(boxAvaliacoes);
      indicadoresGerais.appendChild(boxClientesMesa);
      indicadoresGerais.appendChild(boxReservas);
    } catch (error) {
      console.error("Erro ao carregar os índices:", error);
    }
  };

  const load_reviews = async () => {
    const containerAvaliacao = document.getElementById("reviews");

    const response = await fetch(`${apiUrl}/estabelecimento/${id}/avaliacoes`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    const data = await response.json();

    if (data.length === 0) {
      containerAvaliacao.innerHTML = "<p class='text-gray-600 text-center w-full'>Nenhuma avaliação encontrada.</p>";
      return
    }

    data.reverse().forEach(avaliacao => {
      const cardAvaliacao = document.createElement('article');
      cardAvaliacao.classList.add(
        'bg-white', 'rounded-lg', 'shadow-md', 'p-4', 'mb-4', 'w-full',
        'flex', 'flex-col', 'gap-2'
      );

      // Convert rating to stars
      const stars = "★".repeat(Math.floor(avaliacao.nota)) +
        "☆".repeat(5 - Math.floor(avaliacao.nota));

      cardAvaliacao.innerHTML = `
          <div class="flex items-center justify-between">
            <span class="font-semibold text-lg cursor-pointer" onclick="window.location.href = '/perfil/avaliador/${avaliacao.emailAvaliador}'">${avaliacao.nomeAvaliador}</span>
            <span class="text-gray-400 text-sm">${new Date(avaliacao.dataAvaliacao).toLocaleDateString('pt-BR')}</span>
          </div>
          <div class="flex items-center text-yellow-500">
            <span class="text-xl">${stars}</span>
            <span class="ml-2 text-gray-500">(${avaliacao.nota.toFixed(1)})</span>
          </div>
          <p class="text-gray-700 text-left">${avaliacao.descricao}</p>
        `;

      // Carrossel de imagens
      const carouselContainer = document.createElement('div');
      carouselContainer.classList.add('flex', 'gap-2', 'mt-2', 'overflow-x-auto', 'p-1');

      if (avaliacao.imagensAvaliacao && avaliacao.imagensAvaliacao.length > 0) {
        avaliacao.imagensAvaliacao.forEach((imagem) => {
          const image = document.createElement('img');
          image.src = `data:image/png;base64,${imagem.blobImagem}`;
          image.alt = imagem.nomeImagem + '-' + imagem.idImagem;
          image.classList.add('h-20', 'w-20', 'object-cover', 'rounded-md', 'shadow-sm');
          carouselContainer.appendChild(image);
        });
      } else {
        // Mensagem "sem fotos" se não houver imagens
        const noPhotosMessage = document.createElement('p');
        noPhotosMessage.textContent = "sem fotos";
        noPhotosMessage.classList.add('text-gray-400', 'text-center', 'w-full');
        carouselContainer.appendChild(noPhotosMessage);
      }

      // Append the carousel to the review card
      cardAvaliacao.appendChild(carouselContainer);
      containerAvaliacao.appendChild(cardAvaliacao);
    });
  };

  const load_reservas = async () => {
    const pendentesContainer = document
      .getElementById("reservas-pendentes-container")
      .querySelector(".reservas-list");
    const aprovadasContainer = document
      .getElementById("reservas-aprovadas-container")
      .querySelector(".reservas-list");
  
    try {
      const response = await fetch(
        `${apiUrl}/estabelecimento/${id}/reservas`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );
  
      if (!response.ok) {
        throw new Error("Erro ao carregar reservas.");
      }
  
      const reservas = await response.json();
  
      // Limpa os containers antes de adicionar novos elementos
      pendentesContainer.innerHTML = "";
      aprovadasContainer.innerHTML = "";
  
      if (reservas.length === 0) {
        pendentesContainer.innerHTML =
          "<p class='text-gray-600 text-center w-full'>Nenhuma reserva pendente encontrada.</p>";
        aprovadasContainer.innerHTML =
          "<p class='text-gray-600 text-center w-full'>Nenhuma reserva aprovada encontrada.</p>";
        return;
      }
  
      reservas.forEach((reserva) => {
        const reservaElement = document.createElement("div");
        reservaElement.classList.add(
          "bg-white",
          "rounded-lg",
          "shadow-md",
          "p-4",
          "mb-4",
          "flex",
          "flex-col",
          "gap-2",
          "max-w-sm"
        );
  
        // Formatar a data da reserva
        const reservaDate = new Date(reserva.dataHoraReserva);
        const dataReserva = reservaDate.toLocaleDateString("pt-BR");
        const horaReserva = reservaDate.toLocaleTimeString("pt-BR", {
          hour: "2-digit",
          minute: "2-digit",
        });
  
        // Adiciona informações da reserva ao elemento
        reservaElement.innerHTML = `
          <div class="flex flex-wrap justify-between items-center">
            <span class="font-semibold text-lg cursor-pointer" onclick="window.location.href = '/perfil/estabelecimento/${reserva.emailAvaliador}'">${reserva.nomeAvaliador}</span>
            <span class="text-gray-400 text-sm">Para o dia ${dataReserva} às ${horaReserva}</span>
          </div>
          <p class="text-gray-700 text-left break-words hyphens-auto w-full">
            <span class="text-gray-900 font-semibold">Mesa: </span>${reserva.mesaReserva.nomeMesa}
          </p>
          <div class="text-sm text-gray-600 mt-2 flex gap-2">
            ${
              reserva.statusReserva === "PENDENTE"
                ? `
                  <button 
                    class="text-red-500 border border-red-500 px-3 py-1 rounded-lg hover:bg-red-500 hover:text-white transition"
                    onclick="rejeitarReserva('${reserva.id}')">
                    Rejeitar Reserva
                  </button>
                  <button 
                    class="text-green-500 border border-green-500 px-3 py-1 rounded-lg hover:bg-green-500 hover:text-white transition"
                    onclick="aprovarReserva('${reserva.id}')">
                    Aprovar Reserva
                  </button>
                `
                : `<span class="text-${reserva.statusReserva === "APROVADA" ? 'green' : 'orange'}-500">Status: ${reserva.statusReserva}</span>`
            }
          </div>
        `;

        // Adiciona reserva ao container correto com base no status
        if (reserva.statusReserva === "PENDENTE") {
          pendentesContainer.appendChild(reservaElement);
        } else if (reserva.statusReserva === "APROVADA") {
          aprovadasContainer.appendChild(reservaElement);
        }
      });

      // Se não houver reservas em um dos grupos, mostra mensagem padrão
      if (pendentesContainer.children.length === 0) {
        pendentesContainer.innerHTML =
          "<p class='text-gray-600 text-center w-full'>Nenhuma reserva pendente encontrada.</p>";
      }
      if (aprovadasContainer.children.length === 0) {
        aprovadasContainer.innerHTML =
          "<p class='text-gray-600 text-center w-full'>Nenhuma reserva aprovada encontrada.</p>";
      }
    } catch (error) {
      console.error("Erro ao carregar reservas:", error);
      pendentesContainer.innerHTML =
        "<p class='text-red-500 text-center w-full'>Erro ao carregar reservas pendentes.</p>";
      aprovadasContainer.innerHTML =
        "<p class='text-red-500 text-center w-full'>Erro ao carregar reservas aprovadas.</p>";
    }
  };
  
  // Função para cancelar a reserva
  (window as any).rejeitarReserva = async (idReserva) => {
    try {
      const response = await fetch(
        `${apiUrl}/reserva/${idReserva}/rejeitar`,
        {
          method: "PUT",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );
  
      if (!response.ok) {
        throw new Error("Erro ao cancelar a reserva.");
      }
  
      alert("Reserva cancelada com sucesso!");
      load_reservas(); // Recarregar as reservas após cancelar
    } catch (error) {
      console.error("Erro ao cancelar a reserva:", error);
      alert("Não foi possível cancelar a reserva.");
    }
  };

  (window as any).aprovarReserva = async (idReserva) => {
    try {
      const response = await fetch(`${apiUrl}/reserva/${idReserva}/aprovar`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      });
  
      if (!response.ok) {
        throw new Error("Erro ao aprovar a reserva.");
      }
  
      alert("Reserva aprovada com sucesso!");
      load_reservas(); // Atualiza a lista de reservas
    } catch (error) {
      console.error("Erro ao aprovar a reserva:", error);
      alert("Não foi possível aprovar a reserva.");
    }
  };

  const load_formdata = (data) => {
    (document.getElementById("name-field") as HTMLInputElement).value =
      data.nomeEstabelecimento;
    (document.getElementById("tel-field") as HTMLInputElement).value =
      data.telefoneEstabelecimento;

    if (data.localidadeEstabelecimento) {
      // Remove o último ponto e vírgula e divide a string por ";"
      const enderecoArray = data.localidadeEstabelecimento.split(";");

      // Desestrutura os valores
      const [regiao, bairro, rua, numero, complemento] = enderecoArray;

      (document.getElementById("region-field") as HTMLInputElement).value =
        regiao;
      (document.getElementById("bairro-field") as HTMLInputElement).value =
        bairro;
      (document.getElementById("street-field") as HTMLInputElement).value =
        rua;
      (document.getElementById("number-field") as HTMLInputElement).value =
        numero;
      (
        document.getElementById("additional-field") as HTMLInputElement
      ).value = complemento;
    }

    if (data.descricaoEstabelecimento) {
      (
        document.getElementById("description-field") as HTMLInputElement
      ).value = data.descricaoEstabelecimento;
    }
  };

  document.getElementById("add-tag-action").addEventListener("click", () => {
    document.getElementById("tag-form").classList.toggle("hidden");
  });

  document
    .getElementById("add-image-action")
    .addEventListener("click", () => {
      document.getElementById("image-form").classList.toggle("hidden");
    });

  document.getElementById("tag-form").addEventListener("submit", (event) => {
    event.preventDefault();

    const tag_field = (
      document.getElementById("tag-field") as HTMLInputElement
    ).value;

    fetch(`${apiUrl}/estabelecimento/${id}/tags/${tag_field}`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then(() => load_tags())
      .catch((error) => console.error(error));
  });

  document
    .getElementById("image-form")
    .addEventListener("submit", (event) => {
      event.preventDefault();

      const image_field = (
        document.getElementById("image-field") as HTMLInputElement
      ).files[0];
      parseToBase64(image_field)
        .then((base64String) => {
          const image = base64String.replace(
            /^data:image\/[a-z]+;base64,/,
            ""
          ); // Remove prefixo da imagem base64

          fetch(`${apiUrl}/estabelecimento/${id}/imagens`, {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({ imagem: image }), // Envia a imagem no corpo
          })
            .then((response) => {
              if (!response.ok) throw new Error("Erro ao enviar imagem");
              load_carousel();
            })
            .catch((error) => console.error("Erro:", error));
        })
        .catch((error) => {
          console.error("Erro ao converter imagem para Base64:", error);
        });
    });

  const delete_tag = async (tag) => {
    try {
      const response = await fetch(
        `${apiUrl}/estabelecimento/${id}/tags/${tag}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.ok) {
        load_tags(); // Recarrega as tags após a exclusão
      } else {
        console.error("Erro ao excluir tag");
      }
    } catch (error) {
      console.error("Erro:", error);
    }
  };

  // Mostrar/Esconder formulário de edição ao clicar no ícone de edição
  const edit_action = document.getElementById("edit-action");
  edit_action.addEventListener("click", () => {
    document.getElementById("edit-section").classList.toggle("hidden");
    document.getElementById("business-fields").classList.remove("hidden");
  });

  const parseToBase64 = (file: File): Promise<string> => {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result as string); // Garantindo que o resultado é string
      reader.onerror = (error) => reject(error);
    });
  };

  const edit_form = document.getElementById("edit-form") as HTMLFormElement;

  edit_form.addEventListener("submit", async (event) => {
    event.preventDefault();

    // Obter valores dos campos do formulário
    const name_field = (
      document.getElementById("name-field") as HTMLInputElement
    ).value;
    const tel_field = (
      document.getElementById("tel-field") as HTMLInputElement
    ).value;

    // Verificar se os campos estão vazios
    if (!name_field) {
      return alert("O campo Nome é obrigatório.");
    }
    if (!tel_field) {
      return alert("O campo Telefone é obrigatório.");
    }

    let body: any = {
      id: id,
      nome: name_field,
      telefone: tel_field,
    };

    const category_field = document.getElementById(
      "category-field"
    ) as HTMLSelectElement;

    if (!category_field.value) return alert("Selecione uma categoria.");

    const descricao_field = (
      document.getElementById("description-field") as HTMLInputElement
    ).value;
    const regiao_field = (
      document.getElementById("region-field") as HTMLInputElement
    ).value;
    const bairro_field = (
      document.getElementById("bairro-field") as HTMLInputElement
    ).value;
    const rua_field = (
      document.getElementById("street-field") as HTMLInputElement
    ).value;
    const numero_field = (
      document.getElementById("number-field") as HTMLInputElement
    ).value;
    const acrescimos_field = (
      document.getElementById("additional-field") as HTMLInputElement
    ).value;

    const location_field = `${regiao_field};${bairro_field};${rua_field};${numero_field};${acrescimos_field || " "}`;

    body.localidade = location_field.toLocaleUpperCase();
    body.descricao = descricao_field;
    body.categoria =
      category_field.options[category_field.selectedIndex].text;
    body.dataCriacao = data_criacao;

    // Adicionando imagens se forem selecionadas
    const profileImage = (
      document.getElementById("profile-image") as HTMLInputElement
    ).files[0];
    const bannerImage = (
      document.getElementById("banner-image") as HTMLInputElement
    ).files[0];

    if (profileImage) {
      const profileBase64 = await parseToBase64(profileImage);
      body.charImagemPerfil = profileBase64.replace(
        /^data:image\/[a-z]+;base64,/,
        ""
      );
    } else body.charImagemPerfil = profile;

    if (bannerImage) {
      const bannerBase64 = await parseToBase64(bannerImage);
      body.charImagemBanner = bannerBase64.replace(
        /^data:image\/[a-z]+;base64,/,
        ""
      );
    } else body.charImagemBanner = banner;

    try {
      const response = await fetch(
        `${apiUrl}/estabelecimento/${id}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify(body),
        }
      );

      if (response.ok) {
        alert("Perfil atualizado com sucesso!");
        window.location.reload();
      } else {
        alert("Erro ao atualizar o perfil.");
      }
    } catch (error) {
      console.error("Erro:", error);
      alert("Ocorreu um erro ao enviar os dados.");
    }
  });

  let isModalDeleteOpen = false;

  const toggleModalDelete = () => {
    isModalDeleteOpen = !isModalDeleteOpen;
    const modal = document.getElementById("modal-delete");
    modal.classList.toggle("hidden", !isModalDeleteOpen);
  };

  const handleDelete = async () => {
    const password = (document.getElementById("password") as HTMLInputElement)
      .value;
    let isPasswordValid = false;

    password.trim().length > 0
      ? (isPasswordValid = await bcrypt.compare(password, pwd))
      : alert("Senha não pode estar vazia"); // Comparação da senha

    if (isPasswordValid) {
      // Senha correta, procede com a exclusão
      const deleteResponse = await fetch(
        `${apiUrl}/estabelecimento/${id}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (deleteResponse.ok) {
        alert("Perfil excluído com sucesso.");
        Cookies.remove("token");
        window.location.href = "/login";
      } else {
        alert("Erro ao excluir o perfil.");
      }

      toggleModalDelete();
    } else {
      alert("Senha incorreta. Tente novamente.");
    }
  };

  // Mostrar modal de exclusão ao clicar no ícone de exclusão
  const delete_action = document.getElementById("delete-action");
  delete_action.addEventListener("click", toggleModalDelete);

  // Cancela a excluão e fecha o modal
  document
    .getElementById("cancel-delete")
    .addEventListener("click", toggleModalDelete);

  // Confirma a exclusão e aciona handleDelete
  document
    .getElementById("confirm-delete")
    .addEventListener("click", handleDelete);

  // Função para abrir o modal de avaliação
  (window as any).openRateModal = () => {
    // Update modal content with the establishment name and ID
    document.getElementById("nome-estabelecimento").textContent =
      document.getElementById("profile-name").textContent;
    document.getElementById("rate-modal").dataset.establishmentId = id;

    // Show the modal
    document.getElementById("rate-modal").classList.remove("hidden");
  };

  const openReservaModal = (idMesa, nomeMesa) => {
    // Update modal content with the establishment name and ID
    document.getElementById("titulo-reserva").textContent = "Fazer reserva para " + nomeMesa;
    document.getElementById("reserva-modal").dataset.establishmentId = id;
    document.getElementById("reserva-modal").dataset.tableId = idMesa;

    // Show the modal
    document.getElementById("reserva-modal").classList.remove("hidden");
  };

  const openDeleteConfirmationModal = (idMesa, nomeMesa) => {
    const deleteModal = document.getElementById("delete-confirmation-modal");
    const deleteMessage = document.getElementById("delete-message");

    // Atualiza a mensagem do modal com o ID da mesa
    deleteMessage.innerHTML = `Tem certeza que quer excluir <i class="font-semibold">${nomeMesa}</i>?`;

    // Mostra o modal
    deleteModal.classList.remove("hidden");

    // Configura o evento de clique para o botão "Apagar"
    document.getElementById("confirm-delete-table").addEventListener("click", async () => {
      await deleteTable(idMesa);  // Chama a função para deletar a mesa
      deleteModal.classList.add("hidden"); // Esconde o modal após a exclusão
    });

    // Configura o evento de clique para o botão "Cancelar"
    document.getElementById("cancel-delete-table").addEventListener("click", () => {
      deleteModal.classList.add("hidden"); // Esconde o modal sem deletar
    });
  };

  // Função para abrir o modal de mesa
  const openTableModal = (idMesa = 0, nome = "", descricao = "", capacidade = 1, isEditing = false) => {
    // Update modal content with the establishment ID and modal fields
    const tableModal = document.getElementById("table-modal");
    tableModal.dataset.isEditing = String(isEditing);
    tableModal.dataset.establishmentId = id;

    if(nome) (document.getElementById("nome-mesa") as HTMLInputElement).value = nome;
    if(descricao) (document.getElementById("descricao-mesa") as HTMLInputElement).value = descricao;
    (document.getElementById("capacidade-mesa") as HTMLInputElement).value = String(capacidade);

    if(isEditing)
      document.getElementById("table-title").innerHTML = `Editar ${nome} <i class="hidden" id="table-id">${idMesa}</i>`
    else document.getElementById("table-title").innerText = 'Adicionar Mesa';

    // Show the modal
    tableModal.classList.remove("hidden");
  }; 
  (window as any).openTableModal = () => openTableModal();

  const deleteTable = async (idMesa: any) => {
    try {
      const response = await fetch(`${apiUrl}/mesa/${idMesa}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
  
      if (response.ok) 
        location.reload();
      else alert("Erro ao deletar a mesa.");
    } catch (error) {
      console.error("Erro:", error);
      alert("Ocorreu um erro ao deletar a mesa.");
    }
  };
} else {
  console.error("Token não encontrado");
}

