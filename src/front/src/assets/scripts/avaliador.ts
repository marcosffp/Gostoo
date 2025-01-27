import { jwtDecode } from "jwt-decode";
import Cookies from "js-cookie";
import bcrypt from "bcryptjs";

const token = Cookies.get("token");
const emailParam = document.getElementById("email-param").textContent;
const apiUrl = import.meta.env.PUBLIC_API_URL;

interface JwtPayload {
  type: number;
  sub: string;
  iat: number;
  exp: number;
  id: number;
}

if (token) {
  const decodedToken: JwtPayload = jwtDecode(token);
  const email = decodedToken.sub;
  const type = decodedToken.type;
  const id = decodedToken.id;

  let pwd = "";
  let banner = "";
  let profile = "";

  fetch(`${apiUrl}/avaliador/${emailParam}`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
  })
    .then((response) => response.json())
    .then((data) => {
      pwd = data.senhaAvaliador;

      document.getElementById("profile-name").textContent =
        data.nomeAvaliador;
      (document.getElementById("profile-img") as HTMLImageElement).alt =
        `Foto de ${data.nomeAvaliador}`;

      // Mostrar informações do Avaliador
      document.getElementById("reviewer-info").classList.remove("hidden");
      document.getElementById("reviewer-tel").textContent =
        data.telefoneAvaliador;
      document.getElementById("reviewer-email").textContent =
        data.emailAvaliador;
      document.getElementById("birth").textContent =
        data.dataNascimentoAvaliador;

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

          // Carregar as imagens
          load_images(data),

          // Carregar avaliaçoes
          load_reviews(),

          // Carrega as reservas
          load_reservas(),

          // Carregar dados do formulario de edição
          load_formdata(data)
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
    if (email != data.emailAvaliador) {
      document.getElementById("profile-actions").classList.add("hidden");
      document.querySelector(".section-nav").classList.add("hidden");
    }
  };

  // Função para converter blobImagem em base64
  const convertToBase64 = (imageBlob) => {
    return `data:image/png;base64,${imageBlob}`;
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

  const load_reviews = async () => {
    const containerAvaliacao = document.getElementById("reviews");

    const response = await fetch(`${apiUrl}/avaliador/${id}/avaliacoes`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    const data = await response.json();

    data.reverse().forEach((avaliacao) => {
      const cardAvaliacao = document.createElement("article");
      cardAvaliacao.classList.add(
        "bg-white",
        "rounded-lg",
        "shadow-md",
        "p-4",
        "mb-4",
        "w-full",
        "flex",
        "flex-col",
        "gap-2",
      );

      // Convert rating to stars
      const stars =
        "★".repeat(Math.floor(avaliacao.nota)) +
        "☆".repeat(5 - Math.floor(avaliacao.nota));

      cardAvaliacao.innerHTML = `
          <div class="flex items-center justify-between">
            <span class="font-semibold text-lg cursor-pointer" onclick="window.location.href = '/perfil/estabelecimento/${avaliacao.emailEstabelecimento}?establishmentId=${avaliacao.idEstabelecimento}'">${avaliacao.nomeEstabelecimento}</span>
            <span class="text-gray-400 text-sm">${new Date(avaliacao.dataAvaliacao).toLocaleDateString("pt-BR")}</span>
          </div>
          <div class="flex items-center text-yellow-500">
            <span class="text-xl">${stars}</span>
            <span class="ml-2 text-gray-500">(${avaliacao.nota.toFixed(1)})</span>
          </div>
          <p class="text-gray-700 text-left">${avaliacao.descricao}</p>
        `;

      // Carrossel de imagens
      const carouselContainer = document.createElement("div");
      carouselContainer.classList.add(
        "flex",
        "gap-2",
        "mt-2",
        "overflow-x-auto",
        "p-1",
      );

      if (
        avaliacao.imagensAvaliacao &&
        avaliacao.imagensAvaliacao.length > 0
      ) {
        avaliacao.imagensAvaliacao.forEach((imagem) => {
          const image = document.createElement("img");
          image.src = `data:image/png;base64,${imagem.blobImagem}`;
          image.alt = imagem.nomeImagem + "-" + imagem.idImagem;
          image.classList.add(
            "h-20",
            "w-20",
            "object-cover",
            "rounded-md",
            "shadow-sm",
          );
          carouselContainer.appendChild(image);
        });
      } else {
        // Mensagem "sem fotos" se não houver imagens
        const noPhotosMessage = document.createElement("p");
        noPhotosMessage.textContent = "sem fotos";
        noPhotosMessage.classList.add(
          "text-gray-400",
          "text-center",
          "w-full",
        );
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
        `${apiUrl}/avaliador/${id}/reservas`,
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
            <span class="font-semibold text-lg cursor-pointer" onclick="window.location.href = '/perfil/estabelecimento/${reserva.emailEstabelecimento}?establishmentId=${reserva.idEstabelecimento}'">${reserva.nomeEstabelecimento}</span>
            <span class="text-gray-400 text-sm">Para o dia ${dataReserva} às ${horaReserva}</span>
          </div>
          <p class="text-gray-700 text-left break-words hyphens-auto w-full">
            <span class="text-gray-900 font-semibold">Mesa: </span>${reserva.mesaReserva.nomeMesa}
          </p>
          <div class="text-sm text-gray-600 mt-2">
            ${
              reserva.statusReserva === "PENDENTE"
                ? `<button class="text-red-500 border border-red-500 px-3 py-1 rounded-lg hover:bg-red-500 hover:text-white transition" onclick="cancelarReserva('${reserva.id}')">Cancelar Reserva</button>`
                : `<span class="text-${reserva.statusReserva === "APROVADA" ? 'green' : 'orange'}-500">Status: ${reserva.statusReserva}</span>`
            }
          </div>
        `;
  
        // Adiciona reserva ao container correto com base no status
        if (reserva.statusReserva === "PENDENTE") {
          pendentesContainer.appendChild(reservaElement);
        } else {
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
  (window as any).cancelarReserva = async (idReserva) => {
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

  const load_formdata = (data) => {
    (document.getElementById("name-field") as HTMLInputElement).value =
      data.nomeAvaliador;
    (document.getElementById("tel-field") as HTMLInputElement).value =
      data.telefoneAvaliador;

    if (data.dataNascimentoAvaliador) {
      (document.getElementById("birth-field") as HTMLInputElement).value =
        data.dataNascimentoAvaliador;
    }
  };

  // Mostrar/Esconder formulário de edição ao clicar no ícone de edição
  const edit_action = document.getElementById("edit-action");
  edit_action.addEventListener("click", () => {
    document.getElementById("edit-section").classList.toggle("hidden");
    document.getElementById("reviewer-fields").classList.remove("hidden");
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

    // Campos específicos de Avaliador
    const birth_field = (
      document.getElementById("birth-field") as HTMLInputElement
    ).value;
    if (!birth_field)
      return alert("O campo Data de Nascimento é obrigatório.");

    body.dataNascimento = birth_field;

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
        "",
      );
    } else body.charImagemPerfil = profile;

    if (bannerImage) {
      const bannerBase64 = await parseToBase64(bannerImage);
      body.charImagemBanner = bannerBase64.replace(
        /^data:image\/[a-z]+;base64,/,
        "",
      );
    } else body.charImagemBanner = banner;

    try {
      const response = await fetch(`${apiUrl}/avaliador/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(body),
      });

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
        `${apiUrl}/${type === 1 ? "estabelecimento" : "avaliador"}/${id}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        },
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
} else {
  console.error("Token não encontrado");
}