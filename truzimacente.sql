PGDMP      -                |            travelagency    16.2    16.2 "               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    62078    travelagency    DATABASE     �   CREATE DATABASE travelagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE travelagency;
                postgres    false            �            1259    62083    hotel    TABLE     L  CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name text NOT NULL,
    hotel_city text NOT NULL,
    hotel_region text NOT NULL,
    hotel_fulladress text NOT NULL,
    hotel_email text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    hotel_free_parking boolean NOT NULL,
    hotel_free_wifi boolean NOT NULL,
    hotel_swimming_pool boolean NOT NULL,
    hotel_fitness_center boolean NOT NULL,
    hotel_concierge boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_room_services boolean NOT NULL,
    hotel_pansion_type text[]
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    62088    hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    62094    pansion    TABLE     a   CREATE TABLE public.pansion (
    pansion_id integer NOT NULL,
    pansion_type text NOT NULL
);
    DROP TABLE public.pansion;
       public         heap    postgres    false            �            1259    62099    pansion_pansion_id_seq    SEQUENCE     �   ALTER TABLE public.pansion ALTER COLUMN pansion_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pansion_pansion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    62100    reservation    TABLE     P  CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    reservation_hotel_id integer NOT NULL,
    reservation_room_id integer NOT NULL,
    reservation_guest_fullname text NOT NULL,
    reservation_guest_id text NOT NULL,
    reservation_guest_mpno text NOT NULL,
    reservation_guest_email text NOT NULL,
    reservation_checkin_date date NOT NULL,
    reservation_checkout_date date NOT NULL,
    reservation_child_count integer NOT NULL,
    reservation_adult_count integer NOT NULL,
    reservation_total_price integer NOT NULL,
    reservation_date date NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    62105    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    62106    room    TABLE     k  CREATE TABLE public.room (
    room_id integer NOT NULL,
    room_hotel_id integer NOT NULL,
    room_season_id integer NOT NULL,
    room_type text NOT NULL,
    room_number text NOT NULL,
    room_capacity integer NOT NULL,
    room_adult_price integer NOT NULL,
    room_child_price integer NOT NULL,
    room_stock_quantity integer NOT NULL,
    room_bed_count integer NOT NULL,
    room_square_meters integer NOT NULL,
    room_has_tv boolean NOT NULL,
    room_has_mini_bar boolean NOT NULL,
    room_has_gaming_console boolean NOT NULL,
    room_has_safe_box boolean NOT NULL,
    room_has_projector boolean NOT NULL,
    room_pansion_type text NOT NULL,
    room_reservation_id integer,
    room_season_name text NOT NULL,
    room_hotel_name text NOT NULL,
    room_hotel_city text NOT NULL,
    room_season_start date NOT NULL,
    room_season_end date NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    62111    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            �            1259    62112    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    season_hotel_id integer NOT NULL,
    season_start_date date NOT NULL,
    season_end_date date NOT NULL,
    season_name text NOT NULL,
    season_hotel_name text NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    62117    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223            �            1259    62118    users    TABLE     �   CREATE TABLE public.users (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_pass text NOT NULL,
    user_perm text NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    62123    user_user_id_seq    SEQUENCE     �   ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    225                      0    62083    hotel 
   TABLE DATA             COPY public.hotel (hotel_id, hotel_name, hotel_city, hotel_region, hotel_fulladress, hotel_email, hotel_phone, hotel_star, hotel_free_parking, hotel_free_wifi, hotel_swimming_pool, hotel_fitness_center, hotel_concierge, hotel_spa, hotel_room_services, hotel_pansion_type) FROM stdin;
    public          postgres    false    215   \.                 0    62094    pansion 
   TABLE DATA           ;   COPY public.pansion (pansion_id, pansion_type) FROM stdin;
    public          postgres    false    217   �0                 0    62100    reservation 
   TABLE DATA           U  COPY public.reservation (reservation_id, reservation_hotel_id, reservation_room_id, reservation_guest_fullname, reservation_guest_id, reservation_guest_mpno, reservation_guest_email, reservation_checkin_date, reservation_checkout_date, reservation_child_count, reservation_adult_count, reservation_total_price, reservation_date) FROM stdin;
    public          postgres    false    219   z1       
          0    62106    room 
   TABLE DATA           �  COPY public.room (room_id, room_hotel_id, room_season_id, room_type, room_number, room_capacity, room_adult_price, room_child_price, room_stock_quantity, room_bed_count, room_square_meters, room_has_tv, room_has_mini_bar, room_has_gaming_console, room_has_safe_box, room_has_projector, room_pansion_type, room_reservation_id, room_season_name, room_hotel_name, room_hotel_city, room_season_start, room_season_end) FROM stdin;
    public          postgres    false    221   �1                 0    62112    season 
   TABLE DATA           �   COPY public.season (season_id, season_hotel_id, season_start_date, season_end_date, season_name, season_hotel_name) FROM stdin;
    public          postgres    false    223   3                 0    62118    users 
   TABLE DATA           I   COPY public.users (user_id, user_name, user_pass, user_perm) FROM stdin;
    public          postgres    false    225   �3                  0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 11, true);
          public          postgres    false    216                       0    0    pansion_pansion_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.pansion_pansion_id_seq', 7, true);
          public          postgres    false    218                       0    0    reservation_reservation_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 1, true);
          public          postgres    false    220                       0    0    room_room_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.room_room_id_seq', 6, true);
          public          postgres    false    222                       0    0    season_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.season_season_id_seq', 6, true);
          public          postgres    false    224                       0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 4, true);
          public          postgres    false    226            j           2606    62125    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    215            l           2606    62127    pansion pansion_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pansion
    ADD CONSTRAINT pansion_pkey PRIMARY KEY (pansion_id);
 >   ALTER TABLE ONLY public.pansion DROP CONSTRAINT pansion_pkey;
       public            postgres    false    217            n           2606    62129    reservation reservation_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_guest_id, reservation_guest_mpno, reservation_guest_email);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    219    219    219            p           2606    62131    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    221            r           2606    62133    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    223            t           2606    62135    users user_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 9   ALTER TABLE ONLY public.users DROP CONSTRAINT user_pkey;
       public            postgres    false    225               �  x��T]o�@|^~�)O��D6��x�P� A�@I�/�9��]twEU�{�g� US��N��e���6|1���A;.�+?�gm~�U�Ρ���{�q}�׀5 z=�P=ؖ�y�}��|�؇v����4���Rpg�_7��dC)o'*��{~�Ò�k��j�`&p'`,�k�$��y�Q�φ��O�p�����l5��GJq�V��N�ɕ���B9�M�z�ah_���&N�������E(ξ���r�Fsȹ�z���e�|����~��9r������Ь��/�3,�=&�3a3#�b��q;b��$$w;��!�$6|P�8SM<�K�Z��FA�m��UЬ�aO�<R��^[���Z���r���.�V�P��a���4��V��[�	�պuaY(�evωC���,dA�(�wW�Ӆ�{4ĬA�kr�2�]�#/��{�zd���߃�G�%w��B������>EE�/��g^0���w���N��W�Շ�p�l*���l����p�a"�PZX�S�p�UA�j`�&�����	��N�I���H�}���7F+�l���ʹ�_>=�r�'�:0N��
�6�:�/�#���0KO�c�>��2>Ъ��FS%�%���Y�����n��l���k�y��a\�H�I������j��7��         p   x�3��))JTp�����K�)-�,K�2�D�s:��($�(8�&f�%�p�p����(8�'�p�rz$�A9f�A���
�y9�\�H�\+��d��K��������� �})�         a   x�3�4B�Ң��<�ʔ���"NCS3 �073�4053773�0���L+sHL���K���4202�50�50�3M989M� *h�kd����� iw6      
     x����j�0���S������V�^���Ff2������l�F�%pHr���	A�d�]�$�D���x�ᲊf�J�4"1�͹�?53ȋ4����JS��v�d��i�w`�@`(�~�+��$��t0����t�˾����}���婾A�晖�B��G�ݐ�V��$;ܧ�J+�xVq���[�B�z@�FS�/���g3���wm��a�hs#v�˶���>��x�4^,��8�����6�c���:/�k4�~��-���y�7�ִ�         {   x�3�4�4202�50�50�2-A��P__� N��ļ����.#�bC�bS]cC�pO�4�Ɯ��MNM,�LIUJ-�/*�2���a8�zSN�����$f�)�䧤�r����0M}� �C�         J   x�3�LL��̃��.��~\F���9������F�&���>����\Ɯ�%�ى�)�eP�&�I�%�h�b���� ��     