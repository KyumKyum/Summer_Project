import express, {Request, Response} from 'express';

export const handleGenerateRequest = async (req: Request, res: Response) => {
  return res.status(200).json({
    status: 200,
    message: "Test"
  });
}