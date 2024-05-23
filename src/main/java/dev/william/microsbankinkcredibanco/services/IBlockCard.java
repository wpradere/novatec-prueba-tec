package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.models.request.BlockCardRequest;
import dev.william.microsbankinkcredibanco.models.response.BlockCardResponse;

public interface IBlockCard extends CrudServiceGeneric<BlockCardRequest, BlockCardResponse,Long>{

    BlockCardResponse blockCard (BlockCardRequest request);
}
