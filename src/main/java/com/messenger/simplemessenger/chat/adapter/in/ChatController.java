package com.messenger.simplemessenger.chat.adapter.in;

import com.messenger.simplemessenger.chat.model.ChatMemberId;
import com.messenger.simplemessenger.chat.port.in.service.ChatMemberUseCase;
import com.messenger.simplemessenger.chat.port.in.service.ChatUseCase;
import com.messenger.simplemessenger.chat.port.in.service.MessageUseCase;
import org.openapitools.api.ChatsApi;
import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.UUID;

@Controller("chat")
public class ChatController implements ChatsApi {

    @Autowired
    private ChatUseCase chatUseCase;
    @Autowired
    private MessageUseCase messageUseCase;
    @Autowired
    private ChatMemberUseCase chatMemberUseCase;

    @Override
    public ResponseEntity<ChatResponse> createChat(ChatRequest chatRequest){
        return new ResponseEntity<>(chatUseCase.createChat(chatRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ChatResponse> updateChat(ChatRequest chatRequest){
        return new ResponseEntity<>(chatUseCase.updateChat(chatRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ChatResponse> findChat(UUID id){
        return new ResponseEntity<>(chatUseCase.findChatById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteChat(UUID id){
        chatUseCase.deleteChatById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MessageResponse> createMessage(UUID chatId, MessageRequest messageRequest){
        return new ResponseEntity<>(messageUseCase.createMessage(messageRequest, chatId), HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<MessageResponse> updateMessage(UUID messageId, String message){
        return new ResponseEntity<>(messageUseCase.updateMessage(messageId, message), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteMessage(UUID messageId){
        messageUseCase.deleteMessageById(messageId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ChatMemberResponse> createMember(ChatMemberRequest chatMemberRequest){
        return new ResponseEntity<>(chatMemberUseCase.createChatMember(chatMemberRequest),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> changeMemberRole(UUID chatId, UUID memberId, UserRole userRole) {
        chatMemberUseCase.changeMemberRole(new ChatMemberId(chatId, memberId), userRole);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteMember(UUID chatId, UUID memberId){
        chatMemberUseCase.deleteChatMemberById(chatId,memberId);
        return ResponseEntity.noContent().build();
    }

}
