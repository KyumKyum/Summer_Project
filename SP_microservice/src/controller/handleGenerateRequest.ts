import express, {Request, Response} from 'express';
import { genAvatarReq } from '../types/avatar';
import { genAvatar } from '../service/avatar/genAvatar';
import { BadRequestError } from './error';

export const handleGenerateRequest = async (req: Request, res: Response) => {
    const body: genAvatarReq | undefined = req.body;
    let avatarUrl: string = '';
    
    //TODO: Add Auth system using redis 
    try{
        if(typeof body !== 'undefined'){
            avatarUrl = genAvatar(body.name, body.size);
        }else{
            throw new BadRequestError("Bad Request: Undefined Body");
        }

        return res.json({
            status: 200,
            avatarUrl
        });

    }catch(e: any){
        if(e instanceof BadRequestError){
            return res.json({
                status: 400,
                message: e.message
            });
        }

        return res.json({
            status: 400,
            message: "Unexpected Error: " + e.toString()
        });
    }
    
}