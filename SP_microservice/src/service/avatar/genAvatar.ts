import { genHexColor } from "../utils/genHex";
import { buildUrl } from "build-url-ts";

const BASE_URL:string = "https://source.boringavatars.com";

export const genAvatar = (name:string, size:number):string => {
    const colors: string[] = [];
    
    for(let i = 0; i < 5; i++){
        const color:string = genHexColor();
        colors.push(color);
    }
    // avatar = avatar.slice(0, -1); //( Remove last comma)
    // avatar = avatar.concat('?square');
    const avatar:string = buildUrl(BASE_URL, {
        path: `/beam/${size}/${name}`,
        queryParams: {
            colors,
            square: 'square'
        }
    })

    return avatar;
}