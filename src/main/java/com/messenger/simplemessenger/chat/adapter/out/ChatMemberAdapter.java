package com.messenger.simplemessenger.chat.adapter.out;

import com.messenger.simplemessenger.chat.mapper.ChatMemberMapper;
import com.messenger.simplemessenger.chat.model.ChatEntity;
import com.messenger.simplemessenger.chat.model.ChatMemberEntity;
import com.messenger.simplemessenger.chat.port.out.repository.ChatMemberRepository;
import com.messenger.simplemessenger.chat.model.dto.ChatMember;
import com.messenger.simplemessenger.chat.model.ChatMemberId;
import com.messenger.simplemessenger.chat.port.out.ChatMemberPort;
import com.messenger.simplemessenger.chat.port.out.repository.ChatRepository;
import com.messenger.simplemessenger.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.ChatMemberRequest;
import org.openapitools.model.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatMemberAdapter implements ChatMemberPort {

    private final ChatMemberRepository chatMemberRepository;
    private final ChatRepository chatRepository;
    private final ChatMemberMapper chatMemberMapper;

    @Override
    public ChatMember createChatMember(ChatMemberRequest chatMemberRequest) {

        ChatMemberEntity chatMemberEntity = chatMemberRepository.save(chatMemberMapper.toEntity(chatMemberRequest));


        return chatMemberMapper.toDomain(chatMemberEntity);
    }

    @Override
    public void deleteChatMemberById(ChatMemberId id) {
        chatMemberRepository.deleteById(id);
    }

    @Override
    public void changeMemberRole(ChatMemberId chatMemberId, UserRole userRole) {
        var chatMember  = chatMemberRepository.findById(chatMemberId)
                .orElseThrow(
                        () -> new NoSuchElementException("Member with chatId: %s userId: %s not found".formatted(chatMemberId.getChat(),chatMemberId.getUser()))
                );
        if(userRole.equals(UserRole.OWNER)){
            changeOwner(chatMember);
        }
        else{


            chatMember.setUserRole(userRole);
            chatMemberRepository.save(chatMember);
        }
    }

    // TODO: Remove Column Owner from DB so the Logic is more efficient
    private void changeOwner(ChatMemberEntity newOwner) {
        ChatEntity chat = newOwner.getChat();
        UserEntity currentOwner = newOwner.getChat().getOwner();

        if(currentOwner.getId().equals(newOwner.getUser().getId()))
            return ;

        ChatMemberEntity currentOwnerMember =  chat.getMembers().stream().filter(member -> member.getUser().getId().equals(currentOwner.getId())).findFirst().get();

        // Change the Member Roles
        currentOwnerMember.setUserRole(UserRole.ADMIN);
        newOwner.setUserRole(UserRole.OWNER);
        // Set new Owner for Chat
        chat.setOwner(newOwner.getUser());
        chatMemberRepository.saveAll(List.of(currentOwnerMember,newOwner));
        chatRepository.save(chat);
    }
}
