/* eslint-disable */
import { TypedDocumentNode as DocumentNode } from '@graphql-typed-document-node/core';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
};

export type BlogPost = Node & {
  __typename?: 'BlogPost';
  content: Scalars['String']['output'];
  date: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
  title: Scalars['String']['output'];
  url: Scalars['String']['output'];
};

export type Mutation = {
  __typename?: 'Mutation';
  createBlogPost: BlogPost;
  selectCookiePreferences?: Maybe<PrivacyPreferences>;
};


export type MutationCreateBlogPostArgs = {
  content: Scalars['String']['input'];
  name: Scalars['String']['input'];
  title: Scalars['String']['input'];
};


export type MutationSelectCookiePreferencesArgs = {
  result?: InputMaybe<SelectCookieResult>;
};

export type Node = {
  id: Scalars['ID']['output'];
};

export type PrivacyPreferences = {
  __typename?: 'PrivacyPreferences';
  allowedDomainLogging?: Maybe<Scalars['Boolean']['output']>;
  allowedRequestLogging?: Maybe<Scalars['Boolean']['output']>;
  cookiePromptDismissed?: Maybe<Scalars['Boolean']['output']>;
  id?: Maybe<Scalars['ID']['output']>;
};

export type Query = {
  __typename?: 'Query';
  getBlogPosts: Array<BlogPost>;
  viewer?: Maybe<ViewerContext>;
};

export enum SelectCookieResult {
  Accept = 'Accept',
  Deny = 'Deny'
}

export type UserInfo = {
  profilePictureURL: Scalars['String']['output'];
};

export type ViewerContext = {
  __typename?: 'ViewerContext';
  id?: Maybe<Scalars['ID']['output']>;
  locale?: Maybe<Scalars['String']['output']>;
  privacyPreferences?: Maybe<PrivacyPreferences>;
};

export type PrivacyPreferencesQueryVariables = Exact<{ [key: string]: never; }>;


export type PrivacyPreferencesQuery = { __typename?: 'Query', viewer?: { __typename?: 'ViewerContext', privacyPreferences?: { __typename?: 'PrivacyPreferences', allowedDomainLogging?: boolean | null, allowedRequestLogging?: boolean | null } | null } | null };

export type CookieShownQueryVariables = Exact<{ [key: string]: never; }>;


export type CookieShownQuery = { __typename?: 'Query', viewer?: { __typename?: 'ViewerContext', id?: string | null, privacyPreferences?: { __typename?: 'PrivacyPreferences', id?: string | null, cookiePromptDismissed?: boolean | null, allowedDomainLogging?: boolean | null, allowedRequestLogging?: boolean | null } | null } | null };

export type DisableCookiePopupMutationVariables = Exact<{
  cookiesEnabled: SelectCookieResult;
}>;


export type DisableCookiePopupMutation = { __typename?: 'Mutation', selectCookiePreferences?: { __typename?: 'PrivacyPreferences', id?: string | null, cookiePromptDismissed?: boolean | null, allowedDomainLogging?: boolean | null, allowedRequestLogging?: boolean | null } | null };


export const PrivacyPreferencesDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"query","name":{"kind":"Name","value":"PrivacyPreferences"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"viewer"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"privacyPreferences"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"allowedDomainLogging"}},{"kind":"Field","name":{"kind":"Name","value":"allowedRequestLogging"}}]}}]}}]}}]} as unknown as DocumentNode<PrivacyPreferencesQuery, PrivacyPreferencesQueryVariables>;
export const CookieShownDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"query","name":{"kind":"Name","value":"CookieShown"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"viewer"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"id"}},{"kind":"Field","name":{"kind":"Name","value":"privacyPreferences"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"id"}},{"kind":"Field","name":{"kind":"Name","value":"cookiePromptDismissed"}},{"kind":"Field","name":{"kind":"Name","value":"allowedDomainLogging"}},{"kind":"Field","name":{"kind":"Name","value":"allowedRequestLogging"}}]}}]}}]}}]} as unknown as DocumentNode<CookieShownQuery, CookieShownQueryVariables>;
export const DisableCookiePopupDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"mutation","name":{"kind":"Name","value":"DisableCookiePopup"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"cookiesEnabled"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"SelectCookieResult"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"selectCookiePreferences"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"result"},"value":{"kind":"Variable","name":{"kind":"Name","value":"cookiesEnabled"}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"id"}},{"kind":"Field","name":{"kind":"Name","value":"cookiePromptDismissed"}},{"kind":"Field","name":{"kind":"Name","value":"allowedDomainLogging"}},{"kind":"Field","name":{"kind":"Name","value":"allowedRequestLogging"}}]}}]}}]} as unknown as DocumentNode<DisableCookiePopupMutation, DisableCookiePopupMutationVariables>;