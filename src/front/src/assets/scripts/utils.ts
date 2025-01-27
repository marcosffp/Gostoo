export const getModal = (message) => {
  return `<div id="error-modal" class="fixed inset-0 flex items-center justify-center bg-black z-50 bg-opacity-50">
        <div class="bg-white p-6 rounded shadow-lg">
            <h2 class="text-xl font-bold mb-4">Erro</h2>
            <p id="error-message">${message}</p>
            <button id="close-modal" class="mt-4 bg-red-500 text-white px-4 py-2 rounded">Fechar</button>
        </div>
    </div>`;
};
