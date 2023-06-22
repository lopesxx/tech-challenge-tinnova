const readline = require('readline');
const rdInterface = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function calcSomaMultiplos(x) {
    let soma = 0;

    for (let i = 1; i < x; i++) {
        if (i % 3 === 0 || i % 5 === 0) {
        soma += i;
        }
    }

    return soma;
}

rdInterface.question('Informe um número base para calcularmos a soma dos múltiplos de 3 ou 5 menores que esse número informado: ', (num) => {
    num = parseInt(num);

    if (isNaN(num)  ) {
    console.log('Digite um número válido!');
    rdInterface.close();
    return;
    }

    const somaMultiplos = calcSomaMultiplos(num);
    console.log(`A soma de todos os números múltiplos de 3 ou 5 menores que ${num} é ${somaMultiplos}.`);

    rdInterface.close();
});
