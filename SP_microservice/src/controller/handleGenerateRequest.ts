import express, {Request, Response} from 'express';
import { genAvatarReq } from '../types/avatar';
import { genAvatar } from '../service/avatar/genAvatar';
import { BadRequestError } from './error';
import { checkValidReq } from '../service/auth/checkValidReq';
import { AuthResponse } from '../types/authResponse';

export const handleGenerateRequest = async (req: Request, res: Response) => {
    const body: genAvatarReq | undefined = req.body;
    let avatarUrl: string = '';

    console.log(body)
    
    try{
        if(typeof body !== 'undefined'){
            //* Auth
            const authRes: AuthResponse = await checkValidReq(body.keyVal);
            console.log(authRes)

            if(!authRes.ok){
                throw new BadRequestError(`Bad Request: ${authRes.message}`);
            }

            avatarUrl = genAvatar(body.name, body.size);
        }else{
            throw new BadRequestError("Bad Request: Undefined Body");
        }

        return res.json({
            ok: true,
            status: 200,
            avatarUrl
        });

    }catch(e: any){
        if(e instanceof BadRequestError){
            return res.json({
                ok: false,
                status: 400,
                message: e.message
            });
        }

        return res.json({
            ok: false,
            status: 400,
            message: "Unexpected Error: " + e.toString()
        });
    }
    
}