import { AddIcon, DragHandleIcon } from "@chakra-ui/icons";
import {
    Menu,
    MenuButton,
    MenuList,
    MenuItem,
    Box,
    Flex,
    Spacer,
    Image,
    Link as ChakraLink,
} from "@chakra-ui/react";
import { Link } from "react-router-dom";

const Navbar = () => {
    return (
        <Box>
            <Flex bg={"blackAlpha.800"} alignItems={"center"} boxShadow={"xl"} w={"100%"}>
                <Box p={"2"}>
                    <ChakraLink as={Link} to={"/"}>
                        <Image
                            boxSize={"40px"}
                            src={"/public/logo-widgets.jpeg"}
                            alt={"logo"}
                        ></Image>
                    </ChakraLink>
                </Box>
                <Spacer></Spacer>
                <Box p={"2"} marginRight={"7"}>
                    <Menu isLazy>
                        <MenuButton color="white">Widgets</MenuButton>
                        <MenuList>
                            <MenuItem as={Link} to="/listWidget">
                                <DragHandleIcon mr={4} /> Widget list
                            </MenuItem>
                            <MenuItem as={Link} to="/createWidget" onClick={() => localStorage.clear()}>
                                <AddIcon mr={4} /> Add new Widget
                            </MenuItem>
                        </MenuList>
                    </Menu>
                </Box>
            </Flex>
        </Box>
    );
};

export default Navbar;
