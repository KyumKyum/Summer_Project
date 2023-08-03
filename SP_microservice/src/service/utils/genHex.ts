//* Author: Jay Lim
//* Generate random hex code.

export const genHexColor = ():string => {
    let color:string = '';
    for(let i = 0; i < 6; i++){
        const val:number = Math.floor(16 * Math.random()); //* 0 ~ 16
        color = color.concat(val.toString(16)); //* to hex.
    }
    return color;
}