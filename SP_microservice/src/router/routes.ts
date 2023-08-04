import express, {Express, Response, Router} from 'express';
import { handleGenerateRequest } from '../controller/handleGenerateRequest';

export const genReqRouter:Router = express.Router();

genReqRouter.post('/', handleGenerateRequest);

